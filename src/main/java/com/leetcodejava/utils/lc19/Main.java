package com.leetcodejava.utils.lc19;
/*
Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
*/

public class Main {

	public static class Node 
	{
		public Object val;
		public Node next;
		
		public Node(Object v, Node nextNode)
		{
			val = v;
			next = nextNode;
		}
	}
	
	
	public static void main(String[] args)
	{

		Node n0 = new Node(5, null);
		Node n1 = new Node(4, n0);
		Node n2 = new Node(3, n1);
		Node n3 = new Node(2, n2);
		Node n4 = new Node(1, n3);

		printNodes(n4);
		
		printNodes(removeNth(n4, 2));
	}

	public static Node removeNth(Node first, int n)
	{
		Node prev = null;
		Node p = first;
		Node q = first;
		
		for(int i = 0; i < n; i++)
		{
			q = q.next;
		}
		while(q != null)
		{
			prev = p;
			p = p.next;
			q = q.next;
		}
		
		prev.next = p.next;
		
		return first;
	}
	
	public static void printNodes(Node first)
	{
		for(Node curr = first; curr != null; curr = curr.next)
		{
			System.out.println(curr.val);
		}
	}
}
