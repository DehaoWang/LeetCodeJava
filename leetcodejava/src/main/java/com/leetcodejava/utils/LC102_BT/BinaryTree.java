package com.leetcodejava.utils.LC102_BT;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class BinaryTree 
{
	public Node root;
	
	public static class Node
	{
		public Object val;
		public Node left;
		public Node right;
		
		// FOR BFS & DFS
		public int dist = 0;
		public boolean visited = false;
		
		public Node(Object v)
		{
			val = v;
			left = null;
			right = null;
		}
	}
	
	public BinaryTree()
	{
		root = null;
	}
	
	public void setRoot(Object v)
	{
		root = new Node(v);
	}
	public void addLeft(Node n, Object v)
	{
		n.left = new Node(v);
	}
	public void addRight(Node n, Object v)
	{
		n.right = new Node(v);
	}
	
	// for LC102
	public static void preOrderTraversalRecursive(Node top)
	{
		
		if(top != null)
		{
			System.out.println(top.val);
			preOrderTraversalRecursive(top.left);
			preOrderTraversalRecursive(top.right);
		}
	}
	public static void inOrderTraversalRecursive(Node top)
	{
		
		if(top != null)
		{
			inOrderTraversalRecursive(top.left);
			System.out.println(top.val);
			inOrderTraversalRecursive(top.right);
		}
	}
	public static void postOrderTraversalRecursive(Node top)
	{
		
		if(top != null)
		{
			postOrderTraversalRecursive(top.left);
			postOrderTraversalRecursive(top.right);
			System.out.println(top.val);
		}
	}
	
	public static void levelOrderTraversal(Node top)
	{
		Queue<Object> q = new LinkedList<Object>();
		
		if(top != null)
		{
			q.add(top);
		}
		while(q.size() != 0)
		{
			Node n = (Node) q.poll();
			System.out.println(n.val);
			if(n.left != null)
			{
				q.add(n.left);
			}
			if(n.right != null)
			{
				q.add(n.right);
			}
		}
	}

	// for LC107
	public static void reversedLevelOrderTraversal(Node top)
	{
		Queue<Node> q = new LinkedList<Node>();
		Stack<Node> s = new Stack<Node>();
		if(top != null)
		{
			q.add(top);
		}
		while(q.size() != 0)
		{
			Node n = (Node) q.poll();
			s.add(n);
			if(n.left != null)
			{
				q.add(n.left);
			}
			if(n.right != null)
			{
				q.add(n.right);
			}
		}
		while(s.size() != 0)
		{
			Node n = (Node) s.pop();
			System.out.println(n.val);
		}
		
	}

	// for LC100
	// Recursive solution
	public static boolean areSameTreeRecursive(Node top0, Node top1)
	{
		if(top0 != null && top1 != null && top0.val.equals(top1.val))
		{
			return areSameTreeRecursive(top0.left, top1.left) && areSameTreeRecursive(top0.right, top1.right);
		}
		// ???????????????
		else if(top0 == null && top1 == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	// Iterative solution
	public static boolean areSameTreeIterative(Node top0, Node top1)
	{
		Stack<Node> s = new Stack<Node>();
		s.push(top0);
		s.push(top1);
		
		while(s.size() != 0)
		{
			Node p = s.pop();
			Node q = s.pop();
			if(p == null && q == null)
			{
				continue;
			}
			else if(p != null && q != null && p.val.equals(q.val))
			{
				s.push(p.left);
				s.push(q.left);
				s.push(p.right);
				s.push(q.right);
			}
			else
			{
				return false;
			}
		}
		return true;
	}
	
	// for LC101
	// Recursive solution
	public static boolean isSymmetricRecursive(Node top)
	{
		if(top == null)
		{
			return true;
		}
		else
		{
			return isSymmetricallySame(top.left, top.right);
		}
	}
	
	// symmetrically same
	public static boolean isSymmetricallySame(Node n0, Node n1)
	{
		if(n0 != null && n1 != null && n0.val.equals(n1.val))
		{
			return isSymmetricallySame(n0.left, n1.right) && isSymmetricallySame(n0.right, n1.left);
		}
		else if(n0 == null && n1 == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	// Iterative solution
	public static boolean isSymmetricIterative(Node top)
	{
		if(top == null)
		{
			return true;
		}
		else 
		{
//			Queue q = new LinkedList();
			Stack<Node> s = new Stack<Node>();
			s.push(top.left);
			s.push(top.right);
			
			while(s.size() != 0)
			{
				Node p = s.pop();
				Node q = s.pop();
				if(p == null && q == null)
				{
					continue;
				}
				else if(p != null && q != null && p.val.equals(q.val))
				{
					s.push(p.left);
					s.push(q.right);
					s.push(p.right);
					s.push(q.left);
				}
				else
				{
					return false;
				}
			}
		}
		return true;
	}
	
	// Iterative solution by Queue
	public static boolean isSymmetricIterativeByQueue(Node top)
	{
		if(top == null)
		{
			return true;
		}
		else 
		{
			Queue<Node> que = new LinkedList<Node>();
			que.add(top.left);
			que.add(top.right);
			
			while(que.size() != 0)
			{
				Node p = que.poll();
				Node q = que.poll();
				if(p == null && q == null)
				{
					continue;
				}
				else if(p != null && q != null && p.val.equals(q.val))
				{
					que.add(p.left);
					que.add(q.right);
					que.add(p.right);
					que.add(q.left);
				}
				else
				{
					return false;
				}
			}
		}
		return true;
	}
	
	// for LC110 & LC104
	public static int maxDepthOfTree(Node top)
	{
		if(top == null)
		{
			return -1;
		}
		else
		{
			return 1 + Math.max(maxDepthOfTree(top.left), maxDepthOfTree(top.right));
		}
	}
	
	public static boolean isBalanced(Node top)
	{
		// important: null entry!
		if(top == null)
		{
			return true;
		}
//		System.out.println("TOP: " + top.val);
//		System.out.println("left.depth: " + depthOfTree(top.left));
//		System.out.println("right.depth: " + depthOfTree(top.right));
		else if(Math.abs(maxDepthOfTree(top.left) - maxDepthOfTree(top.right)) > 1)
		{
			return false;
		}
		else
		{
			return isBalanced(top.left) && isBalanced(top.right);
		}
	}
	
	// for LC111
	public static int minDepthOfTree(Node top)
	{
		if(top == null)
		{
			return -1;
		}
		else
		{
			return 1 + Math.min(minDepthOfTree(top.left), minDepthOfTree(top.right));
		}
	}
	
	// for LC112
	public static boolean sumPathExist(Node top, int target)
	{
		if(top != null && top.left == null && top.right == null && Integer.parseInt(top.val.toString()) == target)
		{
			return true;
 		}	
//		if(top == null && target == 0)
//		{
//			return true;
//		}
		else if(top.left != null || top.right != null)
		{
			return sumPathExist(top.left, target - Integer.parseInt(top.val.toString())) || sumPathExist(top.right, target - Integer.parseInt(top.val.toString()));
		}
		else
		{
			return false;
		}
	}
	
	// for LC114
	public static void preOrderTraversalIterative(Node top)
	{
		if(top == null)
		{
			return;
		}
		else
		{
			Stack<Node> s = new Stack<Node>();
			s.add(top);
			while(s.size() > 0)
			{
				Node n = s.pop();
//				if(n == null)
//					continue;
				System.out.println(n.val);
				if(n.right != null)
				{
					s.add(n.right);
				}
				if(n.left != null)
				{
					s.add(n.left);
				}		
			}
		}
	}
	
	// for LC94
	public static void inOrderTraversalIterative(Node top)
	{
		if(top == null)
		{
			return;
		}
		else
		{
			Stack<Node> s = new Stack<Node>();
			Node n = top;
//			s.push(n);
			while(s.size() > 0 || n != null)
			{
				if(n != null)
				{
					s.push(n);			
					n = n.left;	
				}
				else
				{
					n = s.pop();
					System.out.println(n.val);
					n = n.right;
//					s.push(n.right);
				}
			}	
		}
	}
	
	// for LC94 Morris
	public static void inOrderMorris(Node top)
	{
		if(top == null)
		{
			return;
		}
		else
		{
			Node curr = top;
			while(curr != null)
			{
				if(curr.left == null)
				{
					System.out.println(curr.val);
					curr = curr.right;
				}
				else
				{
					Node morr = curr.left;
					while(morr.right != null && morr.right != curr)
					{
						morr = morr.right;
					}
					if(morr.right != curr)
					{
//						System.out.println(curr.val);
						morr.right = curr;
						curr = curr.left;
					}
					else
					{
						System.out.println(curr.val);
						morr.right = null;
						curr = curr.right;
					}
				}
			}
		}
	}
	
	// for LC114 Morris
	public static void preOrderMorris(Node top)
	{
		if(top == null)
		{
			return;
		}
		else
		{
			Node curr = top;
			while(curr != null)
			{
				if(curr.left == null)
				{
					System.out.println(curr.val);
					curr = curr.right;
				}
				else
				{
					Node morr = curr.left;
					while(morr.right != null && morr.right != curr)
					{
						morr = morr.right;
					}
					if(morr.right != curr)
					{
						System.out.println(curr.val);
						morr.right = curr;
						curr = curr.left;
					}
					else
					{
//						System.out.println(curr.val);
						morr.right = null;
						curr = curr.right;
					}
				}
			}
		}
	}	
	
	public static BinaryTree buildTreeFromInPre(int[] pre, int[] in)
	{
		BinaryTree bt = new BinaryTree();
		System.out.println("???");
		bt.root = buildTreeFromInPre(pre, in, 0, pre.length - 1, 0, in.length - 1);
		return bt;	
	}
	
	// for LC105 ???
	public static Node buildTreeFromInPre(int[] pre, int[] in, int pF, int pL, int iF, int iL)
	{
//		System.out.println(String.format("### L: (%d, %d, %d, %d)", pF, pL, iF, iL));
		
		if(pF > pL || iF > iL)
		{
			return null;
		}
//		else if(pF == pL)
//		{
//			return new Node(pre[pF]);
//		}
		else
		{
			int rootVal = pre[pF];
			Node root = new Node(rootVal);
			
			// root index in In-order sequence
			int rI = findIndex(in, rootVal);
			
			// left size indicating object quantity to the left
			int lS = rI - iF;
			
			System.out.print("rI: " + rI);
			
			System.out.println(", lS: " + lS);
			
			System.out.println(String.format("L: (%d, %d, %d, %d)", pF + 1, pF + lS, iF, rI - 1));
			
			root.left = buildTreeFromInPre(pre, in, pF + 1, pF + lS, iF, rI - 1);
			
			System.out.println(String.format("R: (%d, %d, %d, %d)", pF + lS + 1, pL, rI + 1, iL));
			
			root.right = buildTreeFromInPre(pre, in, pF + lS + 1, pL, rI + 1, iL);
			
			return root;
		}
		
	}
	
	// auxiliary method
	public static int findIndex(int[] a, int target)
	{
//		int index = 0;
		for(int i = 0; i < a.length; i++)
		{
			if(a[i] == target)
			{
				return i;
			}
		}
		// fail to find
		return -1;
	}
	
	// for LC98
	public static boolean isBst(Node top)
	{
		if(top == null)
		{
			return true;
		}
		else if(top.left != null && Integer.parseInt( maxInLeft(top.left).val.toString() ) > Integer.parseInt(top.val.toString()) )
		{
			System.out.println("L " + maxInLeft(top.left).val + " CURR " + top.val);
			return false;
		}
		else if(top.right != null && Integer.parseInt( minInRight(top.right).val.toString()) < Integer.parseInt(top.val.toString()) )
		{
			System.out.println("R " + minInRight(top.right).val + " CURR " + top.val);
			return false;
		}
		else
		{
			return isBst(top.left) && isBst(top.right);
		}
	}
	
	// auxiliary methods
	
	public static Node maxInLeft(Node top)
	{
		while(top.right != null)
		{
			top = top.right;
		}
		return top;
	}
	
	public static Node minInRight(Node top)
	{
		while(top.left != null)
		{
			top = top.left;
		}
		return top;
	}
	
	// for LC98
	public static boolean isBstV2(Node top)
	{
		return isBstUsingLU(top, -10, 10);
	}
	
	public static boolean isBstUsingLU(Node top, int lower, int upper)
	{
		if(top == null)
		{
			return true;
		}
		else
		{
			return Integer.parseInt(top.val.toString()) > lower && Integer.parseInt(top.val.toString()) < upper
					&& isBstUsingLU(top.left, lower, Integer.parseInt(top.val.toString()))
					&& isBstUsingLU(top.right, Integer.parseInt(top.val.toString()), upper);
		}
	}

	// BFS
	public static void printNodesBFS(Node root)
	{
		if(root == null)
		{
			return;
		}
		else
		{
			Queue<Node> queue = new LinkedList<Node>();
			queue.add(root);
//			int distanceToRoot = 0;
			root.dist = 0;
			while( ! queue.isEmpty())
			{
				Node u = queue.poll();
				u.visited = true;
				System.out.println(String.format("[%5s]: distanceToRoot = %d, visited: %b", 
						u.val, u.dist, u.visited));	
				if(u.left != null && !u.left.visited)
				{
					u.left.dist = u.dist + 1;
					queue.add(u.left);
				}
				if(u.right != null && !u.right.visited)
				{
					u.right.dist = u.dist + 1;
					queue.add(u.right);
				}	
			}
		}
	}
	
	// DFS
	public static void printNodesDFS(Node root)
	{
		if(root == null)
		{
			return;
		}
		else
		{
			Stack<Node> stack = new Stack<Node>();
			stack.add(root);
//			int distanceToRoot = 0;
			root.dist = 0;
			while( ! stack.isEmpty())
			{
				Node u = stack.pop();
				u.visited = true;
				System.out.println(String.format("[%5s]: distanceToRoot = %d, visited: %b", 
						u.val, u.dist, u.visited));			
				if(u.right != null && !u.right.visited)
				{
					u.right.dist = u.dist + 1;
					stack.add(u.right);
				}	
				if(u.left != null && !u.left.visited)
				{
					u.left.dist = u.dist + 1;
					stack.add(u.left);
				}	
			}
		}
	}
	
	
	
}