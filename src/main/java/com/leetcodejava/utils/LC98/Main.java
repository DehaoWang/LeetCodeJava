package com.leetcodejava.utils.LC98;


import com.leetcodejava.utils.LC102_BT.BinaryTree;

public class Main {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
	
		BinaryTree testMorrisBT = new BinaryTree();
		
		testMorrisBT.setRoot(6);
		
		testMorrisBT.addLeft(testMorrisBT.root, 2);
		testMorrisBT.addRight(testMorrisBT.root, 7);
		
		testMorrisBT.addLeft(testMorrisBT.root.left, 1);
		testMorrisBT.addRight(testMorrisBT.root.left, 4);
		testMorrisBT.addRight(testMorrisBT.root.right, 9);
		
		testMorrisBT.addLeft(testMorrisBT.root.left.right, 3);
		testMorrisBT.addRight(testMorrisBT.root.left.right, 5);
		testMorrisBT.addLeft(testMorrisBT.root.right.right, 8);
		
		System.out.println(BinaryTree.isBst(testMorrisBT.root));
		System.out.println(BinaryTree.isBstV2(testMorrisBT.root));
	}
	

}
