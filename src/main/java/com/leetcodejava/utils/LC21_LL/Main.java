package com.leetcodejava.utils.LC21_LL;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LinkedList testLL0 = new LinkedList(null);
		
		testLL0.addNodeInFirst(6);
		testLL0.addNodeInFirst(4);
		testLL0.addNodeInFirst(3);
		
		
//		LinkedList.printList(testLL0.first);
		LinkedList.printList(testLL0);
		
		LinkedList testLL1 = new LinkedList(null);
		
		testLL1.addNodeInFirst(7);
		testLL1.addNodeInFirst(5);
		testLL1.addNodeInFirst(2);
		testLL1.addNodeInFirst(1);
		
//		LinkedList.printList(testLL1.first);
		LinkedList.printList(testLL1);
		
//		LinkedList.printList(LinkedList.mergeTwoLists(testLL0.first, testLL1.first));
		LinkedList.printList(LinkedList.mergeTwoLists(testLL0, testLL1));
	}

}
