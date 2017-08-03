package com.leetcodejava.algorithms.LC86;


import com.leetcodejava.algorithms.LC21_LL.LinkedList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LinkedList testLL = new LinkedList(null);
		
		testLL.addNodeInFirst(2);
		testLL.addNodeInFirst(5);
		testLL.addNodeInFirst(2);
		testLL.addNodeInFirst(3);
		testLL.addNodeInFirst(4);
		testLL.addNodeInFirst(1);
		
		LinkedList.printList(testLL);
		LinkedList.printList(LinkedList.partitionList(testLL, 4));
	}

}
