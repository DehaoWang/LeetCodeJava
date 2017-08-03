package com.leetcodejava.algorithms.lc617;


import com.leetcodejava.algorithms.LC102_BT.BinaryTree;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTree bt0 = new BinaryTree();
		bt0.setRoot(1);
		bt0.addLeft(bt0.root, 3);
		bt0.addRight(bt0.root, 2);
		bt0.addLeft(bt0.root.left, 5);

		BinaryTree bt1 = new BinaryTree();
		bt1.setRoot(2);
		bt1.addLeft(bt1.root, 1);
		bt1.addRight(bt1.root, 3);
		bt1.addRight(bt1.root.left, 4);
		bt1.addRight(bt1.root.right, 7);

//		BinaryTree.printNodesBFS(bt0.root);
//      BinaryTree.printNodesBFS(bt1.root);

//        BinaryTree.printNodesDFS(bt0.root);
//        BinaryTree.printNodesDFS(bt1.root);

        BinaryTree.Node newRoot = BinaryTree.merge2Trees(bt0.root, bt1.root);

        BinaryTree.printNodesDFS(newRoot);


	}

}
