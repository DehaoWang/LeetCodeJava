package com.ncfgroup.darwin.opt;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;
import org.neo4j.rest.graphdb.util.QueryResult;

import com.ncfgroup.darwin.data.gdb.GraphDBManager;

/**
 * 
 * <p>Title: Cypher</p>
 * 
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: ncfgroup</p>
 * 
 * @version 1.0
 * 
 * @author wangdehao 
 *
 */
public class Cypher {

	private GraphDBManager gdbm = GraphDBManager.getInstance();
	private static final Logger logger = Logger.getLogger(Cypher.class);
	/**
	 * 
	 * @param pname
	 * @param pvalue
	 * @return
	 */
	public Node locSingle(String pname, String pvalue) {
		List<Node> nodes = cypherByProperty(pname, pvalue);
		if (nodes.size() > 0) {
			return nodes.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param pname
	 * @param pvalue
	 * @return
	 */
	public List<Node> cypherByProperty(String pname, String pvalue) {
		String cypher = "match n where n." + pname + " = '" + pvalue + "' return n";
		logger.info("cypher >> [" + cypher + "]");
		return this.cypher("n", cypher);
	}

	/**
	 * 
	 * @param alias
	 * @param cypher
	 * @return
	 */
	public List<Node> cypher(String alias, String cypher) {
		List<Node> nodes = new ArrayList<Node>();
		List<Map<String,Object>> rets = this.cypher(cypher);
		for(Map<String, Object> map : rets) {
			Node node = (Node)map.get(alias);
			nodes.add(node);
		}
		return nodes;
	}

	/**
	 * 
	 * @param cypher
	 * @return
	 */
	public List<Map<String,Object>> cypher(String cypher) {
		
//		System.out.println(cypher);
		
		RestCypherQueryEngine engine = gdbm.getCypherEngine();
		List<Map<String, Object>> rets = new ArrayList<Map<String, Object>>();
		try {
			Map<String, Object> data = new HashMap<String, Object>();
			
//			System.out.println("$$$");
			QueryResult<Map<String,Object>> result = engine.query(cypher, data);
//			System.out.println("###_" + result);
			for (Map<String, Object> row : result) { 	
				rets.add(row);
			}
		} catch (Exception e) {
			logger.error("", e);
		} finally{
			gdbm.freeCypherEngine(engine);
		}
		return rets;
	}

	/**
	 * 
	 * @param cypher
	 * @return
	 */
	public Map<String, Node> node_map_for_d3_json(String cypher) {
				
		RestCypherQueryEngine engine = gdbm.getCypherEngine();

		Map<String, Node> distinct_nodes = new HashMap<String, Node>();
		
		try {
			Map<String, Object> data = new HashMap<String, Object>();
			

			QueryResult<Map<String, Object>> result = engine.query(cypher, data);

			for (Map<String, Object> row : result) { 
				
//				System.out.println(row.get("nodes").getClass().getName());
				if(! (row.get("nodes") instanceof ArrayList))
				{
					Node single_node = (Node) row.get("nodes"); 
					System.out.println("SINGLE NODE");
					distinct_nodes.put("ID_" + single_node.getId(), single_node);
				}
				else
				{
					@SuppressWarnings("unchecked")
					ArrayList<Node> nodes_in_path = (ArrayList<Node>)row.get("nodes");
					for(Node node : nodes_in_path)
					{
						distinct_nodes.put("ID_" + node.getId(), node);
					}
				}	
			}
			System.out.println("Node_Set_Size: " + distinct_nodes.size());			
		} catch (Exception e) {
			logger.error("", e);
		} finally{
			gdbm.freeCypherEngine(engine);
		}
		return distinct_nodes;
	}

	/**
	 * 
	 * @param cypher
	 * @return
	 */
	public JSONObject all_info_for_d3_json(String cypher) {
			
		System.out.println("CYPHER: " + cypher);
		RestCypherQueryEngine engine = gdbm.getCypherEngine();

		Map<String, Node> distinct_nodes = new HashMap<String, Node>();
		Map<String, Relationship> distinct_links = new HashMap<String, Relationship>();
		try {
			Map<String, Object> data = new HashMap<String, Object>();
			

			QueryResult<Map<String, Object>> result = engine.query(cypher, data);

			for (Map<String, Object> row : result) { 
				
				
				System.out.println("ROW:" + row);
				
				// processing nodes
				@SuppressWarnings("unchecked")
				ArrayList<Node> nodes_in_path = (ArrayList<Node>)row.get("nodes");
				for(Node node : nodes_in_path)
				{
					distinct_nodes.put("Id_" + node.getId(), node);
				}
				
				// processing links
				@SuppressWarnings("unchecked")
				
				
				ArrayList<Relationship> links_in_path = (ArrayList<Relationship>)row.get("rels");
				
				int[] test = new int[links_in_path.size()];
				int index = 0;
				for(Relationship link : links_in_path)
				{
					test[index] = (int) link.getId();
					index ++;
//					System.out.println(links_in_path.size());
					distinct_links.put("Id_" + link.getId(), link);
					
					
//					System.out.println("LINK_ID: " + link.getId());
				}
//				System.out.println("INDEX: " + index);
				if(test[0] == test[links_in_path.size()-1])
				{
					System.out.println("Duplicate Links");
//					System.exit(0);
				}
			}

		} catch (Exception e) {
			logger.error("", e);
		} finally{
			gdbm.freeCypherEngine(engine);
		}
		return get_d3_json(distinct_nodes, distinct_links);
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public JSONObject get_d3_json(Map<String, Node> node_map, Map<String, Relationship> link_map) {
		

		
		
		// INDEX MAP
		Map<Integer, Integer> index_map = new HashMap<Integer, Integer>();
		Integer node_index_in_array = 0;
		
		// NODE part
		JSONObject json_nodes = new JSONObject();
		
		ArrayList<JSONObject> json_node_list = new ArrayList<JSONObject>();
		
		for (Map.Entry<String, Node> entry : node_map.entrySet())
		{	
			// index map building
			index_map.put((Integer)entry.getValue().getProperty("id"), node_index_in_array++);

			// node json building
			JSONObject json_n = new JSONObject();
			json_n.put("name", entry.getValue().getProperty("name"));
			json_n.put("id", entry.getValue().getProperty("id"));
			json_node_list.add(json_n);		
		}
		json_nodes.put("nodes", json_node_list);
			
		// LINK part

		JSONObject json_links = new JSONObject();
		
		ArrayList<JSONObject> json_link_list = new ArrayList<JSONObject>();
		
		for (Map.Entry<String, Relationship> entry : link_map.entrySet())
		{	
			// index map utilizing
			Relationship r = entry.getValue();
			Integer source = index_map.get(r.getStartNode().getProperty("id"));
			Integer target = index_map.get(r.getEndNode().getProperty("id"));
//			System.out.println(source + " : " + (String)r.getStartNode().getProperty("name") + " --> " + target + " : " + (String)r.getEndNode().getProperty("name"));
			
			JSONObject json_l = new JSONObject();
			json_l.put("source", source);
			json_l.put("target", target);
			json_link_list.add(json_l);		
		}
		json_links.put("links", json_link_list);

		
//		System.out.println(json_nodes);
//		System.out.println(index_map);
//		System.out.println(json_links);
		
		
		// RESULT
		JSONObject final_json = new JSONObject();
		
		final_json.putAll(json_nodes);
		final_json.putAll(json_links);
		System.out.println(final_json);
		return final_json;
	}
	
	////////////////////////////////////////////////////////////////////
	//COLORING
	
	/**
	 * 
	 * @param cypher
	 * @return
	 */
	public JSONObject all_info_for_d3_json_coloring(String cypher) {
			
		System.out.println("CYPHER: " + cypher);
		RestCypherQueryEngine engine = gdbm.getCypherEngine();

		Map<String, Node> distinct_nodes = new HashMap<String, Node>();
		Map<String, Relationship> distinct_links = new HashMap<String, Relationship>();
		// accompany distance map for coloring
		Map<String, Integer> distances_to_center = new HashMap<String, Integer>();
		
		try {
			Map<String, Object> data = new HashMap<String, Object>();
			

			QueryResult<Map<String, Object>> result = engine.query(cypher, data);

			for (Map<String, Object> row : result) { 
				
				
//				System.out.println("ROW:" + row);
				
				// processing nodes
				@SuppressWarnings("unchecked")
				ArrayList<Node> nodes_in_path = (ArrayList<Node>)row.get("nodes");
				for(Node node : nodes_in_path)
				{
					distinct_nodes.put("Id_" + node.getProperty("id"), node);
					
					// empty or smaller: update
					if(distances_to_center.get("Id_" + node.getProperty("id")) == null 
							|| nodes_in_path.indexOf(node) < distances_to_center.get("Id_" + node.getProperty("id")))
					{
						distances_to_center.put("Id_" + node.getProperty("id"), nodes_in_path.indexOf(node));
					}
					
				}
				
				// processing links
				@SuppressWarnings("unchecked")
				
				
				ArrayList<Relationship> links_in_path = (ArrayList<Relationship>)row.get("rels");
				for(Relationship link : links_in_path)
				{
					distinct_links.put("Id_" + link.getId(), link);
				}
				
				
			}
			System.out.println(distances_to_center);

		} catch (Exception e) {
			logger.error("", e);
		} finally{
			gdbm.freeCypherEngine(engine);
		}
		return get_d3_json_coloring(distinct_nodes, distinct_links, distances_to_center);
	}
	
	
	
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public JSONObject get_d3_json_coloring(Map<String, Node> node_map, Map<String, Relationship> link_map, Map<String, Integer> dist_map) {
		
		// INDEX MAP
		Map<String, Integer> index_map = new HashMap<String, Integer>();
		Integer node_index_in_array = 0;
		
		// NODE part
		JSONObject json_nodes = new JSONObject();
		
		ArrayList<JSONObject> json_node_list = new ArrayList<JSONObject>();
		
		for (Map.Entry<String, Node> entry : node_map.entrySet())
		{	
			// index map building
			index_map.put((String) entry.getValue().getProperty("id"), node_index_in_array++);

			// node json building
			JSONObject json_n = new JSONObject();
			json_n.put("name", entry.getValue().getProperty("name"));
			json_n.put("id", entry.getValue().getProperty("id"));
			
			json_n.put("group", dist_map.get(entry.getKey()));
			
			json_node_list.add(json_n);		
		}
		json_nodes.put("nodes", json_node_list);
			
		// LINK part

		JSONObject json_links = new JSONObject();
		
		ArrayList<JSONObject> json_link_list = new ArrayList<JSONObject>();
		
		for (Map.Entry<String, Relationship> entry : link_map.entrySet())
		{	
			// index map utilizing
			Relationship r = entry.getValue();
			Integer source = index_map.get(r.getStartNode().getProperty("id"));
			Integer target = index_map.get(r.getEndNode().getProperty("id"));
//			System.out.println(source + " : " + (String)r.getStartNode().getProperty("name") + " --> " + target + " : " + (String)r.getEndNode().getProperty("name"));
			
			JSONObject json_l = new JSONObject();
			json_l.put("source", source);
			json_l.put("target", target);
			json_link_list.add(json_l);		
		}
		json_links.put("links", json_link_list);

		
//		System.out.println(json_nodes);
//		System.out.println(index_map);
//		System.out.println(json_links);
		
		
		// RESULT
		JSONObject final_json = new JSONObject();
		
		final_json.putAll(json_nodes);
		final_json.putAll(json_links);
		System.out.println(final_json);
		return final_json;
	}
	
	//????//
	void testGIT()
	{
		System.out.println();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
