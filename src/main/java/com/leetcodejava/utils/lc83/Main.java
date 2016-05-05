package com.leetcodejava.utils.lc83;

/*
Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
*/

public class Main {

	public static class Node {
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
		Node n0 = new Node(4, null);
		Node n1 = new Node(3, n0);
		Node n2 = new Node(3, n1);
		Node n3 = new Node(3, n2);
		Node n4 = new Node(2, n3);
		Node n5 = new Node(2, n4);
		Node n6 = new Node(1, n5);

		printNodes(n6);
		printNodes(removeDuplicates(n6));
	}

	public static Node removeDuplicates(Node first)
	{
		if(first == null)
		{
			return null;
		}
		else if(first.next == null)
		{
			return first;
		}
		else
		{
			Node indexNode = first;
			for(Node curr = first.next; curr != null; curr = curr.next)
			{
				if(curr.val == indexNode.val)
				{
					indexNode.next = curr.next;
				}
				else
				{
					indexNode = curr;
				}
			}
		}
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
