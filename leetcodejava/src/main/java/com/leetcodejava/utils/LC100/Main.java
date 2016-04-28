package com.leetcodejava.utils.LC100;


import com.leetcodejava.utils.LC102_BT.BinaryTree;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTree bt0 = new BinaryTree();
		bt0.setRoot(3);
		bt0.addLeft(bt0.root, 9);
		bt0.addRight(bt0.root, 20);
		bt0.addLeft(bt0.root.right, 15);
		bt0.addRight(bt0.root.right, 7);
		
		BinaryTree bt1 = new BinaryTree();
		bt1.setRoot(3);
		bt1.addLeft(bt1.root, 9);
		bt1.addRight(bt1.root, 20);
		bt1.addLeft(bt1.root.right, 15);
		bt1.addRight(bt1.root.right, 7);
//		bt1.addLeft(bt1.root.right.right, 8);
		
		boolean testBR = BinaryTree.areSameTreeRecursive(bt0.root, bt1.root);
		System.out.println(testBR);

		boolean testBI = BinaryTree.areSameTreeIterative(bt0.root, bt1.root);
		System.out.println(testBI);

	}

}
