package com.leetcodejava.algorithms.LC21_LL;

public class LinkedList 
{
	// �����ͷ
	public Node first = null;
	
	// ������
	public LinkedList(Node fir)
	{
		first = fir;
	}
	
	// ����ڵ㶨��
	public static class Node
	{
		private int val;
		private Node next;
		
		public Node(int v, Node n)
		{
			val = v;
			next = n;
		}
	}
	
	// ������������
	public void reverseLinkedList()
	{
		// ...
	}
	
//}
	public void addNodeInFirst(int v)
	{
		first = new Node(v, first);
	}
	

	// Node version
	public static void printList(Node fir)
	{
		if(fir == null)
		{
			System.out.println("Empty List");
		}
		System.out.println("Printing:");
		for(Node curr = fir; curr != null; curr = curr.next)
		{
			System.out.println(curr.val);
		}
	}
	
	public static Node mergeTwoLists(Node n0, Node n1)
	{
		if(n0 == null)
		{
			return n1;
		}
		if(n1 == null)
		{
			return n0;
		}
		
		Node dummy = new Node(-1, null);
		Node p = dummy;
		for(; n0 != null && n1 != null; p = p.next)
		{
			if(n0.val < n1.val)
			{
				p.next = n0;
				n0 = n0.next;
			}
			else
			{
				p.next = n1;
				n1 = n1.next;
			}
		}
		p.next = n0 == null ? n1 : n0;
		 
		return dummy.next;
	}
	
	// List version
	public static void printList(LinkedList l)
	{
		if(l.first == null)
		{
			System.out.println("Empty List");
		}
		System.out.println("Printing:");
		for(Node curr = l.first; curr != null; curr = curr.next)
		{
			System.out.println(curr.val);
		}
	}
	
	public static LinkedList mergeTwoLists(LinkedList l0, LinkedList l1)
	{
		if(l0.first == null)
		{
			return l1;
		}
		if(l1.first == null)
		{
			return l0;
		}
		
		Node n0 = l0.first;
		Node n1 = l1.first;
		Node dummy = new Node(-1, null);
		Node p = dummy;
		for(; n0 != null && n1 != null; p = p.next)
		{
			if(n0.val < n1.val)
			{
				p.next = n0;
				n0 = n0.next;
			}
			else
			{
				p.next = n1;
				n1 = n1.next;
			}
		}
		p.next = n0 == null ? n1 : n0;
		 
		return new LinkedList(dummy.next);
	}
	
	// for LC86
	public static Node partitionList(LinkedList ll, int target)
	{

		Node leftDummy = new Node(-1, null);
		Node rightDummy = new Node(-1, null);
		Node l = leftDummy;
		Node r = rightDummy;
		for(Node curr = ll.first; curr != null; curr = curr.next)
		{
			if(curr.val < target)
			{
				l.next = curr;
				l = l.next;
			}
			else
			{
				r.next = curr;
				r = r.next;
			}
		}
		l.next = rightDummy.next;
		// important
		r.next = null;
		
		return leftDummy.next;
	}
	
	// for LC82
	public static Node removeDuplicates(Node first)
	{
		
		
	
		if(first == null)
		{
			return null;
		}
		else
		{
			System.out.println("???");
			
			Node dummy = new Node(-1, null);
			Node prev = dummy;
			Node curr = first;
			Node succ = curr.next;
			int count = 0;
			
			System.out.println(prev.val);
			System.out.println(curr.val);
			System.out.println(succ.val);
			while(succ != null)
			{
				System.out.println("@@@");
				if(succ.val == curr.val)
				{
					count++;
				}
				else if(count > 0)
				{
					curr = succ;
					count = 0;
				}
				else
				{
					prev.next = curr;
					prev = curr;
					curr = curr.next;
				}
				succ = succ.next;
			}
			// testing
			prev.next = curr;
			
			return dummy.next;
		}
	}
	
	// for LC61
	public static Node rotateList(Node first, int k)
	{
		if(first == null)
		{
			return null;
		}
		else
		{
			Node curr = first;
			int len = 1;
			for(; curr.next != null; curr = curr.next)
			{
				len++;
			}
			
			curr.next = first;
			
			for(int i = 0; i < len - k % len; i++)
			{
				curr = curr.next;
			}
			Node newFirst = curr.next;
			curr.next = null;
			return newFirst;
		}
	}
	
	// for LC24
	public static Node swapInPairs(Node first)
	{
		if(first == null || first.next == null)
		{
			return first;
		}
		else
		{
//			Node prev = first;
			Node curr = first;
			Node succ = curr.next;
			curr.next = swapInPairs(succ.next);
			succ.next = curr;
			
			return succ;
		}
		
	}
}
