package com.leetcodejava.algorithms.LC107;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class BinaryTree 
{
	public Node root;
	
	public class Node
	{
		public Object val;
		public Node left;
		public Node right;
		
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
	
	public void preOrderTraversal(Node top)
	{
		
		if(top != null)
		{
			System.out.println(top.val);
			preOrderTraversal(top.left);
			preOrderTraversal(top.right);
		}
	}
	public void inOrderTraversal(Node top)
	{
		
		if(top != null)
		{
			inOrderTraversal(top.left);
			System.out.println(top.val);
			inOrderTraversal(top.right);
		}
	}
	public void postOrderTraversal(Node top)
	{
		
		if(top != null)
		{
			postOrderTraversal(top.left);
			postOrderTraversal(top.right);
			System.out.println(top.val);
		}
	}
	
	public void levelOrderTraversal(Node top)
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

	// ??? REVERSED
	public void reversedLevelOrderTraversal(Node top)
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
//	public void addToFirstOfQueue(LinkedList<Node> q, Node n)
//	{
//		q.addFirst(n);
//		if(n.left != null)
//		addToFirstOfQueue(q, n.left);
//		addToFirstOfQueue(q, n.right);
//	}
}
