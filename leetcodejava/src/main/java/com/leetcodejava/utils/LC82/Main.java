package com.leetcodejava.utils.LC82;

import com.leetcodejava.utils.LC21_LL.LinkedList;

public class Main {
	
	public static void main(String[] args)
	{
		LinkedList testLL0 = new LinkedList(null);
		
//		testLL0.addNodeInFirst(8);
//		testLL0.addNodeInFirst(7);
//		testLL0.addNodeInFirst(6);
		
		testLL0.addNodeInFirst(5);
		testLL0.addNodeInFirst(4);
		testLL0.addNodeInFirst(4);
		testLL0.addNodeInFirst(3);
		testLL0.addNodeInFirst(2);
		testLL0.addNodeInFirst(2);
		testLL0.addNodeInFirst(2);
		testLL0.addNodeInFirst(1);
//		testLL0.addNodeInFirst(3);
		
//		230703196508240724
		
		
		LinkedList.printList(testLL0);
		
		LinkedList.printList(LinkedList.removeDuplicates(testLL0.first));
		
	}

}
