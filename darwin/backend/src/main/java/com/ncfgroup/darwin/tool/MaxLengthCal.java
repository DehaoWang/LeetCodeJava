package com.ncfgroup.darwin.tool;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.traversal.Evaluators;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.graphdb.traversal.Uniqueness;
import org.neo4j.kernel.Traversal;

import com.ncfgroup.darwin.App;
import com.ncfgroup.darwin.data.gdb.GraphDBManager;

//import com.ncfgroup.darwin.dav.obj.RelTypes;

import com.ncfgroup.darwin.opt.Opt;
import com.ncfgroup.darwin.opt.RelTypes;
import com.ncfgroup.darwin.util.Timer;

public class MaxLengthCal {
	
	final int TEST_THRESHOLD = 10000;
	final static int NUMBER_OF_NODE = 3318054;

	File file = null;
	
	Opt find = new Opt();

	private GraphDatabaseService gdb;

	private GraphDBManager gdbm = GraphDBManager.getInstance();
	
	public static void main(String[] args) throws Exception {
		
		Timer timer = new Timer();
		timer.start();		
		App.main(args);		
		
		int startId = Integer.parseInt(args[1]);
		int endId = Integer.parseInt(args[2]);
		String filePath = args[3];
				
		for(int i = 0; i < args.length; i++)
		{
			System.out.println(args[i]);
		}
		
		MaxLengthCal mlct = new MaxLengthCal();
		//1482082 1480114
		
		// CHECH 700697
		mlct.traverseFromAllNodes_Integrated(489, 489, filePath);
//		mlct.traverseFromAllNodes_Integrated(startId, endId, filePath);
		
//		System.out.println(mlct.getMaxLevel_REG(3));
//		mlct.traverseFromAllNodes_INV(startId, endId, filePath);

		
	}
	
	public void cypher() {
//		Cypher cypher = new Cypher();
//		List<Node> nodes = cypher.findNodesByProperty("id", "504");
//		for(Node node : nodes) { 
//			System.out.println(node);
//		}
	}
	
	public void setFile(String path)
	{
		file = new File(path);
	}
	
	public void traveral(int node_id) throws Exception {
		Opt find = new Opt();
		Timer timer = new Timer();
		timer.start();
		Path longestPath = null;
		List<Path> paths  = find.traversal_REG(find.findNodeById(node_id), Direction.OUTGOING);
		int max = 0;
//		System.out.println(find.findNodeById(3));
		
		
		for(Path path : paths) {
//			System.out.println(path);
			int length = path.length();
			if (length >= max)
			{
				max = length;
				longestPath = path;
			}
			int node_cnt = 0;
//			System.out.println("PATH PRINTING:");
			for(Node node: path.nodes())
			{
				System.out.print(node.getProperty("id"));
				if(node_cnt < path.length())
				{
					System.out.print(" -> ");
				}
			
				node_cnt++;
			}
			System.out.println("PATH LENGTH: " + path.length());
		}
		System.out.println("---------------------------");
		System.out.println("RESUSTS:");
		System.out.println("---------------------------");
		System.out.println("PATH COUNT: " + paths.size());
		System.out.println("MAX LENGTH: " + max);		
		System.out.println("---------------------------");
		
		System.out.println("/PATH DETAILS/");	
		System.out.println("***************************************");
		if(longestPath.length() != 0)
			
		System.out.println("START NODE ID:" + longestPath.startNode().getProperty("id"));
		System.out.println("END NODE ID:" + longestPath.endNode().getProperty("id"));
		System.out.println("LENGTH: " + longestPath.length());
		System.out.println("---------------------------");
	
		System.out.println("NODES INFO");
		Set<Long> node_set = new HashSet<Long>();
		System.out.println("NODE LIST");
		
		int node_cnt = 0;
		for(Node node: longestPath.nodes())
		{
			System.out.print(node.getProperty("id"));
			if(node_cnt < longestPath.length())
			{
				System.out.print(" -> ");
			}	
			node_set.add( node.getId());
			node_cnt++;
		}
		System.out.println();
		System.out.println("DISTINCT NODE COUNT: " + node_set.size());
		System.out.println("***************************************");
		System.out.println("LINKS INFO");
		Set<Long> link_set = new HashSet<Long>();
		System.out.println("LINK LIST");
		
		for(Relationship link: longestPath.relationships())
		{
			System.out.print(link.getStartNode().getProperty("id") + " --> " + link.getEndNode().getProperty("id") + " /// ");
			link_set.add(link.getId());
		}
		System.out.println();
		System.out.println("DISTINCT LINK COUNT: " + link_set.size());	
		
		timer.end(" level ");
	
	}

	
	
	public int getMaxLevel_REG(int nodeId) throws Exception {
		Opt find = new Opt();
		Node node = find.findNodeById(nodeId);
		
		
		System.out.println(node.getProperty("id"));
		List<Path> paths  = find.traversal_REG(node, Direction.OUTGOING);
		int max = 0;
		for(Path path : paths) {
			int length = path.length();
			if (length >= max)
			{
				max = length;
			}
		}
		return max;
	}	
	
	public int getMaxLevel_INV(int nodeId) throws Exception {
		Opt find = new Opt();
		Node node = find.findNodeById(nodeId);
		
		
//		System.out.println(node.getDegree(Direction.OUTGOING));
		List<Path> paths  = find.traversal_INV(node, Direction.OUTGOING);
		int max = 0;
		for(Path path : paths) {
			int length = path.length();
			if (length >= max)
			{
				max = length;
			}
		}
		return max;
	}
	
	public void traverseFromAllNodes_Integrated(int startId, int endId, String fullPath) throws Exception {
		File file = new File(fullPath);
//		String lineBreaker = "******************************************************************************************************************\n";
//		FileUtils.writeStringToFile(file, lineBreaker, true);
		
//		Map<Integer, Integer> levelMap = new HashMap<Integer, Integer>();
		
		for(int i = endId; i >= startId; i--)
		{
			Timer timer = new Timer();
			timer.start();
			
			Opt opt = new Opt();
			
			Node startNode = opt.findNodeById(i);
			System.out.println(startNode);
			// REGISTER
			List<Path> paths_reg = opt.traversal_REG(startNode, Direction.OUTGOING);		
			int max_reg = 0;					
			int lv1cnt_reg = 0;
			Set<Node> nodeSet_reg = new HashSet<Node>();	
			
			// INVEST
			List<Path> paths_inv = opt.traversal_INV(startNode, Direction.OUTGOING);
			int max_inv = 0;	
			int lv1cnt_inv = 0;
			Set<Node> nodeSet_inv = new HashSet<Node>();	
			
			// NEW ATTRIBUTES
			Set<Relationship> relSet_inv = new HashSet<Relationship>();
			int lv1sumMoney = 0;
			int allsumMoney = 0;
			
			Set<Node> lv1nodeSet_reg = new HashSet<Node>();
			Set<Node> lv1nodeSet_inv = new HashSet<Node>();
			double lv1riRate = 0.0;
			
			double allriRate = 0.0;
			
			// REG_PART
			for(Path path : paths_reg) {
				
//				for(Node n: path.nodes())
//				{
//					System.out.print(n.getProperty("id") + " --> ");
//				}
//				System.out.println();
				
				int length = path.length();
				
				// MAX LEVEL
				if (length >= max_reg)
				{
					max_reg = length;
				}
				// NODE COUNT ON LV1
				if(length == 1)
				{
					lv1cnt_reg++;
					lv1nodeSet_reg.add(path.endNode());
				}
				// ALL NODE COUNT
				for(Node node: path.nodes())
				{
					if( !node.equals(path.startNode()))
					{
						nodeSet_reg.add(node);
					}				
				}
			}
			
			// INV_PART
//			System.out.println("INV_PATH_COUNT: " + paths_inv.size());
			for(Path path : paths_inv) {
				
				// detail_CHECK
//				for(Node n: path.nodes())
//				{
//					System.out.print(n.getProperty("id") + " --> ");
//				}
//				System.out.println();
				
				int length = path.length();
				
				// MAX LEVEL
				if (length >= max_inv)
				{
					max_inv = length;
				}
				// NODE COUNT ON LV1
				if(length == 1)
				{
//					for(Node n: path.nodes())
//					{
//						System.out.print(n.getProperty("id") + " --> ");
//					}
//					System.out.println();
					
					
					lv1cnt_inv++;
					lv1nodeSet_inv.add(path.endNode());
					lv1sumMoney += (Integer) path.lastRelationship().getProperty("sum");
//					for(Relationship rel: path.relationships())
//					{
//						lv1sumMoney += (Integer)rel.getProperty("sum");
//					}					
				}
				// ALL NODE COUNT
				for(Node node: path.nodes())
				{
					if( !node.equals(path.startNode()))
					{
						nodeSet_inv.add(node);
					}	
				}
				
				for(Relationship rel: path.relationships())
				{
					relSet_inv.add(rel);
				}

			}
			
			for(Relationship rel: relSet_inv)
			{
				allsumMoney += (Integer)rel.getProperty("sum");
			}
			
			int trCnt_lv1 = 0;
			for(Node node: lv1nodeSet_reg)
			{
				if(lv1nodeSet_inv.contains(node))
				{
					trCnt_lv1++;
				}
			}
			if(lv1nodeSet_reg.size() == 0)
			{
				lv1riRate = 0;
			}
			else
			{
				lv1riRate = (double) trCnt_lv1 / lv1nodeSet_reg.size();
			}
			
			int trCnt_all = 0;
			for(Node node: nodeSet_reg)
			{
				if(nodeSet_inv.contains(node))
				{
					trCnt_all++;
				}
			}
			if(nodeSet_reg.size() == 0)
			{
				allriRate = 0;
			}
			else
			{
				allriRate = (double) trCnt_all / nodeSet_reg.size();
			}
			
			
			
			Object id = startNode.getProperty("id");
			
//			startNode.setProperty(arg0, arg1);
//			String nodeProfile =  id + ", " 
//					+ max_reg + ", " + lv1cnt_reg + ", " + nodeSet_reg.size() + ", " 
//					+ max_inv + ", " + lv1cnt_inv + ", " + nodeSet_inv.size() + ", " 
//					+ lv1sumMoney + ", " + allsumMoney + ", " + allriRate
//					+ "\n";
			
			String nodeProfile = String.format("%s,%d,%d,%d,%d,%d,%d,%d,%d,%.2f,%.2f\n", 
					id, 
					max_reg, lv1cnt_reg, nodeSet_reg.size(), 
					max_inv, lv1cnt_inv, nodeSet_inv.size(), 
					lv1sumMoney, allsumMoney, lv1riRate, allriRate);

			
			
			System.out.println("LV1-----------------------------------");
			for(Node n: lv1nodeSet_reg)
			{
				System.out.println(n.getProperty("id"));
			}
			System.out.println(lv1nodeSet_reg.size());
			
			System.out.println("ALL------------------------------------");
			for(Node n: nodeSet_reg)
			{
				System.out.println(n.getProperty("id"));
			}
			System.out.println(nodeSet_reg.size());
			
			
			FileUtils.writeStringToFile(file, nodeProfile, true);
			System.out.println(nodeProfile);
			System.out.println(lv1nodeSet_inv.size());
			timer.end(" time used ");
		}
//		System.out.println("MAX LEVEL: " + maxLevel + " CORRESPONDING ID: " + maxLevelID);
	}
	
	
	public void traverseFromAllNodes_REG(int startId, int endId, String fullPath) throws Exception {
		File file = new File(fullPath);
//		String lineBreaker = "******************************************************************************************************************\n";
//		FileUtils.writeStringToFile(file, lineBreaker, true);
		
//		Map<Integer, Integer> levelMap = new HashMap<Integer, Integer>();
		
		for(int i = endId; i >= startId; i--)
		{
			Timer timer = new Timer();
			timer.start();
			
			Opt opt = new Opt();
			
			Node startNode = opt.findNodeById(i);

			List<Path> paths = opt.traversal_REG(startNode, Direction.OUTGOING);
			int max = 0;
			Set<Node> nodeSet = new HashSet<Node>();
			int lv1cnt = 0;
			
			for(Path path : paths) {
				int length = path.length();
				
				// MAX LEVEL
				if (length >= max)
				{
					max = length;
				}
				// NODE COUNT ON LV1
				if(length == 1)
				{
					lv1cnt++;
				}
				// ALL NODE COUNT
				for(Node node: path.nodes())
				{
					nodeSet.add(node);
				}
			}
			
			Object id = startNode.getProperty("id");
			
//			startNode.setProperty(arg0, arg1);
			String maxLevelOfNode = "id:" + id + ",maxLevel:" + max + ",nodeCntLv1:" + lv1cnt + ",allNodeCnt:" + (nodeSet.size()-1) + "\n";
			FileUtils.writeStringToFile(file, maxLevelOfNode, true);
			System.out.println(maxLevelOfNode);
			
			timer.end(" time used ");
		}
//		System.out.println("MAX LEVEL: " + maxLevel + " CORRESPONDING ID: " + maxLevelID);
	}
	
	public void traverseFromAllNodes_INV(int startId, int endId, String fullPath) throws Exception {
		File file = new File(fullPath);
//		String lineBreaker = "******************************************************************************************************************\n";
//		FileUtils.writeStringToFile(file, lineBreaker, true);
		
//		Map<Integer, Integer> levelMap = new HashMap<Integer, Integer>();
		int maxLevel = 0;
		int maxLevelID = 0;
		
		
		for(int i = endId; i >= startId; i--)
		{
			Timer timer = new Timer();
			timer.start();
			
			Opt opt = new Opt();
			
			Node startNode = opt.findNodeById(i);

			List<Path> paths = opt.traversal_INV(startNode, Direction.OUTGOING);
			int max = 0;
			Set<Node> nodeSet = new HashSet<Node>();
			int lv1cnt = 0;
			
			for(Path path : paths) {
				int length = path.length();
				
				// MAX LEVEL
				if (length >= max)
				{
					max = length;
				}
				// NODE COUNT ON LV1
				if(length == 1)
				{
					lv1cnt++;
				}
				// ALL NODE COUNT
				for(Node node: path.nodes())
				{
					nodeSet.add(node);
				}
			}
			
			Object id = startNode.getProperty("id");
			
//			startNode.setProperty(arg0, arg1);
			String maxLevelOfNode = "id:" + id + ",maxLevel:" + max + ",nodeCntLv1:" + lv1cnt + ",allNodeCnt:" + (nodeSet.size()-1) + "\n";
			FileUtils.writeStringToFile(file, maxLevelOfNode, true);
			System.out.println(maxLevelOfNode);
			
			timer.end(" time used ");
		}
//		System.out.println("MAX LEVEL: " + maxLevel + " CORRESPONDING ID: " + maxLevelID);
	}
	public void countNodes() throws Exception {

		List<Node> nodes = find.getAllNode();
		logger.info("nodes : " + nodes.size());
	}
	
	public Node getNodeById(long id) throws Exception {
		GraphDBManager gdbm = GraphDBManager.getInstance();
		GraphDatabaseService gdb = gdbm.getGraphDB();
		try {
			return gdb.getNodeById(id);
		} catch (Exception e) {
			throw e;
		} finally{
			gdbm.freeGraphDB(gdb);
		}
	}
	
	public void statNodes() throws Exception {

		List<Node> nodes = find.getAllNode();
		int count_node = nodes.size();
		int count_rel = 0;
		for(Node node : nodes) {
			List<Relationship> rels = find.findChildNodes(node, Direction.OUTGOING);
			count_rel = count_rel + rels.size();
		}
		logger.info("nodes=" + count_node + ", rels=" + count_rel);
	}

	private static final Logger logger = Logger.getLogger(MaxLengthCal.class);

	
	
	public void batchTransaction(List<Integer> indexes)
	{
		gdb = gdbm.getGraphDB();
		Map<Integer, Integer> maxLengthMap = new HashMap<Integer, Integer>();
		Transaction tx = gdb.beginTx();
		
		
		Integer maxLevel = 0;
		Integer maxLevelID = 0;
		try 
		{		
			for(Integer index : indexes) 
			{
				try 
				{
	//				System.out.println(index);
					Node node = gdb.getNodeById(index);
					Integer maxLength = 0;
					@SuppressWarnings("deprecation")
					TraversalDescription friendsTraversal = Traversal.description()
							.depthFirst()
							.relationships(RelTypes.REFER_INVEST, Direction.OUTGOING)
							.uniqueness(Uniqueness.NODE_GLOBAL)
//							.uniqueness(Uniqueness.RELATIONSHIP_GLOBAL)
							;
					for (Path path : friendsTraversal
							.evaluator(Evaluators.all())
							.traverse(node)
							){
						if(path.length() > maxLength)
						{
							maxLength = path.length();
							maxLevel = maxLength;
							maxLevelID = index;
						}
					}
					maxLengthMap.put(index, maxLength);
				} 
				catch (Exception e) 
				{
//					logger.warn("", e);
				}
			}
			
	//		System.out.println(maxLengthMap);
			System.out.println("MAX LEVEL: " + maxLevel + " CORRESPONDING ID: " + maxLevelID);
			
			// commit
			tx.success();
		} 
		catch (Exception e) 
		{
			// transcation rollback ?
			tx.failure();
//			throw e;
		} 
		finally
		{
			gdbm.freeGraphDB(gdb);
		}
	}
	
	
	
	
	public void testEfficiency(int numberOfNode) throws Exception
	{
		int MAX_INDEX = 3318053;
		List<Integer> testList = new ArrayList<Integer>();
		for(int i = MAX_INDEX; i > MAX_INDEX - numberOfNode; i--)
		{
			testList.add(i);
		}
		
		for(Integer i : testList)
		{
			System.out.println("PRINTING: " + i);
			
		}
		System.out.println("SIZE: " + testList.size());
		
		
//		Timer t1 = new Timer();
//		t1.start();
//		this.traveraFromAllNodes(MAX_INDEX - numberOfNode + 1, MAX_INDEX);
//		t1.end("PREV TEST T1");
//		System.out.println(t1.usedtime());
//		
//
//		Timer t2 = new Timer();
//		t2.start();
//		this.batchTransaction(testList);
//		t2.end("POST TEST T2");
//		System.out.println(t2.usedtime());
//		System.out.println("NUMBER OF NODES: " + numberOfNode);
//		System.out.println("TIME DIFFERENCE: " + (t1.usedtime()-t2.usedtime()));
		
		
//		Timer t3 = new Timer();
//		t3.start();
//		this.allNodeTest();
//		t3.end("POST TEST T3");
		
		Timer t4 = new Timer();
		t4.start();
		this.threadTestEfficiency(20, numberOfNode);
		t4.end("POST TEST T4");
		
		System.out.println("**************************");
//		System.out.println("T1: " + t1.usedtime());
//		System.out.println("T2: " + t2.usedtime());
		System.out.println("T4: " + t4.usedtime());
	}
	
	@SuppressWarnings("deprecation")
	public void allNodeTest()
	{
		gdb = gdbm.getGraphDB();
		
		Map<Integer, Integer> maxLengthMap = new HashMap<Integer, Integer>();
		Transaction tx = gdb.beginTx();
		
		
		Integer maxLevel = 0;
		Integer maxLevelID = 0;
		
		int cnt = 0;
		
		try 
		{		
			for(Node node : gdb.getAllNodes()) 
			{
				try 
				{
					Integer maxLength = 0;
					@SuppressWarnings("deprecation")
					TraversalDescription friendsTraversal = Traversal.description()
							.depthFirst()
							.relationships(RelTypes.REFER_INVEST, Direction.OUTGOING)
							.uniqueness(Uniqueness.NODE_GLOBAL)
//							.uniqueness(Uniqueness.RELATIONSHIP_GLOBAL)
							;
					for (Path path : friendsTraversal
							.evaluator(Evaluators.all())
							.traverse(node)
							){
						if(path.length() > maxLength)
						{
							maxLength = path.length();
							maxLevel = maxLength;
							maxLevelID = (int)node.getId();
						}
					}
					maxLengthMap.put((int)node.getId(), maxLength);
					String infoLine = "ID: " + node.getId() + " level: " + maxLength;
					System.out.println(infoLine);
					cnt++;
					if(cnt == TEST_THRESHOLD)
					{
						System.exit(0);
					}
				} 
				catch (Exception e) 
				{
//					logger.warn("", e);
				}
			}
			
	//		System.out.println(maxLengthMap);
			System.out.println("MAX LEVEL: " + maxLevel + " CORRESPONDING ID: " + maxLevelID);
			
			// commit
			tx.success();
		} 
		catch (Exception e) 
		{
			// transcation rollback ?
			tx.failure();
//			throw e;
		} 
		finally
		{
			gdbm.freeGraphDB(gdb);
		}
	}
	
	public void threadTestEfficiency(int numberOfThread, int numberOfTestNode)
	{

		int maxId = NUMBER_OF_NODE - 1;
		int minId = NUMBER_OF_NODE - numberOfTestNode;
		
		for(int i = 0; i < numberOfThread; i++)
		{
			int startId = minId + i * numberOfTestNode / numberOfThread;
			int endId = minId + (i+1) * numberOfTestNode / numberOfThread;
//			System.out.println(startId + " : " + endId);
			
//			MaxLengthCalThread mlct = new MaxLengthCalThread(startId, endId);
//			mlct.start();
		}
	}

	
	public void threadTest(int numberOfThread)
	{
		for(int i = 0; i < numberOfThread; i++)
		{
			int startId = i * NUMBER_OF_NODE / numberOfThread;
			int endId = (i+1) * NUMBER_OF_NODE / numberOfThread;
			System.out.println(startId + " : " + endId);
			
///			MaxLengthCalThread mlct = new MaxLengthCalThread(startId, endId);
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
