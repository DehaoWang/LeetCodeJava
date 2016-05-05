package com.ncfgroup.darwin.server;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.neo4j.graphdb.Node;

import com.ncfgroup.darwin.Global;
import com.ncfgroup.darwin.Status;
import com.ncfgroup.darwin.commons.cli.DefaultOption;
import com.ncfgroup.darwin.exception.DefinedException;
import com.ncfgroup.darwin.exception.ParamException;
import com.ncfgroup.darwin.opt.Cypher;
import com.ncfgroup.darwin.util.FormatJsonString;
import com.ncfgroup.darwin.util.MD5;
import com.ncfgroup.darwin.util.Timer;
/**
 * 
 * <p>Title: ServiceCenter</p>
 * 
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: ncfgroup</p>
 * 
 * @version 1.0
 * 
 * @author wangdehao 
 *
 */
public class ServiceCenter {

	/**
	 * 
	 * @param params
	 * @return
	 */
	public String process(String bid, Map<String, String> params) {
		boolean isok = true;
		params = clearInvalidParams(params);

		String result = null;
		Timer timer = new Timer();
		timer.start();
		try {
			String enkey = params.get(DefaultOption.enkey.getOption().getLongOpt());
			String action = params.get(DefaultOption.action.getOption().getLongOpt());

			// Test
			if (enkey == null || action == null) {
				throw new ParamException(Status.Test);

				// enkey verify
			} else if (! verifyEnkey(enkey)) {
				throw new ParamException(Status.UNAUTHORIZED);

				// safemode check
			} else if (safemodeCheck(action)) {
				throw new ParamException(Status.SAFEMODE);

				// Query
			} else if (action.equals(Global.ACTION_Q)) {
				
				Map<String, Object> data = get(params);
//				result = getSuccessResult(data);
				
				
				result = getSuccessResult(data);
				// D3
			} else if (action.equals(Global.ACTION_D)) {
				
				Map<String, Object> data = get_for_d3(params);
//				result = getSuccessResult(data);
				
				
				result = getSuccessResult_for_D3(data);
				// View
			}
			else if (action.equals(Global.ACTION_V)) {
				result = view(params);

			} else {
				throw new ParamException(Status.PARAM_ERROR);
			}

		} catch (Exception e) {
			isok = false;
			result = getErrorResult(e);
			logger.error("", e);
		} finally {
			logger.info(FormatJsonString.format(result));
			timer.end("processing time", logger);
			BLogger.getInstance().oplog(bid, params, timer.usedtime(), isok);
		}
		return result;
	}

	/**
	 * 
	 * @param params
	 * @return
	 */
	public Map<String, Object> get(Map<String, String> params) throws Exception {
		Timer timer = new Timer();	
		Map<String, Object> data = new HashMap<String, Object>();

		// cypher execution
		String cypher_string = params.get("cypher");
		Cypher cypher = new Cypher();
		
		timer.start();
		
		List<Map<String, Object>> results = cypher.cypher(cypher_string);
		data.put("result", results);
		timer.end("get info");
		
		return data;
	}
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> get_for_d3(Map<String, String> params) throws Exception {
		Timer timer = new Timer();	
		Map<String, Object> data = new HashMap<String, Object>();

		// cypher execution
		String cypher_string_invest = params.get("d3_invest");
		String cypher_string_register = params.get("d3_register");
		Cypher cypher = new Cypher();
		timer.start();
//		Map<String, Node> r_D3 = cypher.all_info_for_d3_json(cypher_string);
		
		JSONObject map_d3_invest = cypher.all_info_for_d3_json_coloring(cypher_string_invest);
		JSONObject map_d3_register = cypher.all_info_for_d3_json_coloring(cypher_string_register);
		data.put("invest", map_d3_invest);
		data.put("register", map_d3_register);
		timer.end("get info");	
		return data;
	}
	

	
	
	
	public String view(Map<String, String> params) throws Exception {
		Timer timer = new Timer();
		
		String id = params.get(DefaultOption.id.getOption().getLongOpt());
		String level = params.get(DefaultOption.level.getOption().getLongOpt());

		// cypher execution
		String cypher_string = "match (n:Customer)-[r*" + level + "]->(m:Customer) "
				+ "where n.id = " + id +" return m.id, m.name order by m.id desc;";
		Cypher cypher = new Cypher();
		
		List<Map<String, Object>> results = cypher.cypher(cypher_string);		

		timer.start();
		timer.end("get info");
		List<String> list = new ArrayList<String>();
		
		for(Map<String, Object> row: results) {
			list.add(row.toString());
		}

		String html = Web.createHtml(id, level, list);	
		return html;
	}

	/**
	 * 
	 * @param e
	 * @return
	 */
	private String getErrorResult(Exception e) {
		String result = null;
		if (e instanceof DefinedException) {
			result = getErrorResult(((DefinedException)e).getStatus());

		} else if (e instanceof ConnectException) {
			result = getErrorResult(Status.GDB_CONN_ERROR);

		} else {
			result = getErrorResult(Status.UNKNOW);
		}
		return result;
	}

	/**
	 * 
	 * @param params
	 * @return
	 */
	private Map<String, String> clearInvalidParams(Map<String, String> params) {
		Map<String, String> newmap = new HashMap<String, String>();
		for(Map.Entry<String, String> entry : params.entrySet()) {
			if (! entry.getValue().equals("")) {
				newmap.put(entry.getKey(), entry.getValue());
			}
		}
		return newmap;
	}

	/**
	 * 
	 * @param status
	 * @return
	 */
	public String getErrorResult(Status status) {
		return this.getErrorResult(status.getCode(), status.getDesc());
	}

	/**
	 * 
	 * @param errorCode
	 * @param errorMsg
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getErrorResult(String errorCode, Object errorMsg) {
		JSONObject json = new JSONObject();
		json.put("error_code", errorCode);
		json.put("error_msg", errorMsg);
		return json.toJSONString();
	}

	@SuppressWarnings("unchecked")
	public String getSuccessResult(Map<String, Object> data) {
		JSONObject json = new JSONObject();
		json.put("error_code", Status.OK.getCode());
		json.put("error_msg", Status.OK.getDesc());
		json.put("data", data);
		return json.toJSONString();
	}
	
	@SuppressWarnings("unchecked")
	public String getSuccessResult_for_D3(Map<String, Object> data) {
		JSONObject json = new JSONObject();		
		json.putAll(data);
		return json.toJSONString();
	}
	

	/**
	 * 
	 * @param enkey
	 * @return
	 */
	public static boolean verifyEnkey(String enkey) {
		try {

			if (! AppServer.enkeyenable) {
				logger.warn("enkey-enable is closed");
				return true;
			}

			if (enkey.length() < 5) {
				logger.warn("enkey-length[" + enkey.length() + "] is error");
				return false;
			}

			String time = enkey.substring(0, 4);
			String real_enkey = enkey.substring(4);

			// check expired
			int int_time4 = Integer.parseInt(time);
			// low 4bit
			int int_currtime4 = (int)(System.currentTimeMillis() / 1000) % 10000;		
			int abs_value = Math.abs(int_currtime4 - int_time4);
			if (abs_value > AppServer.enkeyexpire) {
				logger.warn("time[" + int_time4 
						+ "]  has expired, currtime[" 
						+ int_currtime4 + "], expiretime[" + AppServer.enkeyexpire + "s]");
				return false;
			}

			// verify
			String md5 = MD5.GetMD5Code(time + AppServer.enkey);
			// low 4bit
			String server_enkey = md5.substring(md5.length() - 4);
			logger.info("senkey : " + server_enkey);
			if (server_enkey.equals(real_enkey)) {
				return true;
			}
		} catch (Exception e) {
			logger.warn("", e);
		}
		return false;
	}

	/**
	 * 
	 * @param enkey
	 * @return
	 */
	public boolean safemodeCheck(String action) {

		if (AppServer.safemode) {
			logger.warn("safemode-enable is opened");
		} else {
			return false;
		}

		if (Global.alter_actions.contains(action)) {
			logger.warn("no change is permitted when the system is safe mode");
			return true;
		}
		return false;
	}

	private static Logger logger = Logger.getLogger(ServiceCenter.class);

}
