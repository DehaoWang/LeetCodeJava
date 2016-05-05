package com.leetcodejava.utils.LC24;


import com.leetcodejava.utils.LC21_LL.LinkedList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LinkedList testLL0 = new LinkedList(null);
		
		testLL0.addNodeInFirst(7);
		testLL0.addNodeInFirst(6);
		testLL0.addNodeInFirst(5);
		testLL0.addNodeInFirst(4);
		testLL0.addNodeInFirst(3);
		testLL0.addNodeInFirst(2);
		testLL0.addNodeInFirst(1);

		LinkedList.printList(testLL0);
		LinkedList.printList(LinkedList.swapInPairs(testLL0.first));

	}

}
