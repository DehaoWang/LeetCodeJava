package com.leetcodejava.algorithms.LC101;


import com.leetcodejava.algorithms.LC102_BT.BinaryTree;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BinaryTree testBT = new BinaryTree();
		testBT.setRoot(1);
		
		testBT.addLeft(testBT.root, 2);
		testBT.addRight(testBT.root, 2);
		
		testBT.addLeft(testBT.root.left, 3);
		testBT.addRight(testBT.root.left, 4);
		
		testBT.addLeft(testBT.root.right, 4);
		testBT.addRight(testBT.root.right, 3);
		
		boolean testBR = BinaryTree.isSymmetricRecursive(testBT.root);
		System.out.println(testBR);
		
		boolean testBI = BinaryTree.isSymmetricIterative(testBT.root);
		System.out.println(testBI);
		
		boolean testBIQ = BinaryTree.isSymmetricIterativeByQueue(testBT.root);
		System.out.println(testBIQ);
	}

}
