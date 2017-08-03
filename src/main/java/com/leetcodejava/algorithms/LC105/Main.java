package com.leetcodejava.algorithms.LC105;


import com.leetcodejava.algorithms.LC102_BT.BinaryTree;

public class Main {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
	
		int[] testPreOrder = {6, 2, 1, 4, 3, 5, 7, 9, 8};
		int[] testInOrder  = {1, 2, 3, 4, 5, 6 ,7, 8, 9};
		
		// testing phase 1: PASS
//		System.out.println(BinaryTree.findIndex(testPreOreder, 10));
		
		// testing phase 2: XXX
		BinaryTree testBT = BinaryTree.buildTreeFromInPre(testPreOrder, testInOrder);
		BinaryTree.inOrderMorris(testBT.root);
		BinaryTree.preOrderMorris(testBT.root);
		BinaryTree.postOrderTraversalRecursive(testBT.root);
	}
	

}
