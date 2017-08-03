package com.leetcodejava.algorithms.LC111;


import com.leetcodejava.algorithms.LC102_BT.BinaryTree;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTree testBT = new BinaryTree();
		testBT.setRoot(3);
		testBT.addLeft(testBT.root, 9);
		testBT.addRight(testBT.root, 20);
		testBT.addLeft(testBT.root.right, 15);
		testBT.addRight(testBT.root.right, 7);
		testBT.addRight(testBT.root.right.right, 13);
		
		System.out.println(BinaryTree.minDepthOfTree(testBT.root));
	}

}
