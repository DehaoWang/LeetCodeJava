package com.leetcodejava.utils.LC61;
import com.leetcodejava.utils.LC21_LL.LinkedList;

public class Main {
	public static void main(String[] args)
	{
		LinkedList testLL0 = new LinkedList(null);
		
		testLL0.addNodeInFirst(5);
		testLL0.addNodeInFirst(4);
		testLL0.addNodeInFirst(3);
		testLL0.addNodeInFirst(2);
		testLL0.addNodeInFirst(1);
		
		LinkedList.printList(testLL0);
//		LinkedList.rotateList(testLL0.first, 3);
		LinkedList.printList(LinkedList.rotateList(testLL0.first, 2));
	}

}
