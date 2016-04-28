package com.leetcodejava.utils.LC20;

import com.leetcodejava.utils.lc19.Main.Node;

public class StackByLinkedList
{
	public Node first;
	public int size;
	
	public StackByLinkedList()
	{
		first = null;
		size = 0;
	}
	
	public void push(char c)
	{
		Node n = new Node(c, null);
		n.next = first;
		first = n;
		size++;
	}
	
	public char pop()
	{
		char c = '0';
		if(first != null)
		{
			c = first.val.toString().charAt(0);
			first = first.next;
			size--;
		}

		return c;
	}
	
	public int size()
	{
		return size;
	}
}

