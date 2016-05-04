package com.leetcodejava.utils.LC102_BT;

public class Main {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
			
		System.out.println("This is something in a new branch");

		BinaryTree testBT = new BinaryTree();
		testBT.setRoot(3);
		testBT.addLeft(testBT.root, 9);
		testBT.addRight(testBT.root, 20);
		testBT.addLeft(testBT.root.right, 15);
		testBT.addRight(testBT.root.right, 7);
		
		System.out.println("Pre-Order:");
		BinaryTree.preOrderTraversalRecursive(testBT.root);
		
		System.out.println("In-Order:");
		BinaryTree.inOrderTraversalRecursive(testBT.root);
		
		System.out.println("Post-Order:");
		BinaryTree.postOrderTraversalRecursive(testBT.root);
		
		System.out.println("Level-Order:");
		BinaryTree.levelOrderTraversal(testBT.root);
		
		System.out.println("Reversed Level-Order:");
		BinaryTree.reversedLevelOrderTraversal(testBT.root);
		
		// Testing BFS
		System.out.println("Testing BFS:");
		BinaryTree.printNodesBFS(testBT.root);
		
		
		BinaryTree testBST_B = new BinaryTree();
		testBST_B.setRoot(6);
		
		testBST_B.addLeft(testBST_B.root, 2);
		testBST_B.addRight(testBST_B.root, 7);
		
		testBST_B.addLeft(testBST_B.root.left, 1);
		testBST_B.addRight(testBST_B.root.left, 4);
		testBST_B.addRight(testBST_B.root.right, 9);
		
		testBST_B.addLeft(testBST_B.root.left.right, 3);
		testBST_B.addRight(testBST_B.root.left.right, 5);
		testBST_B.addLeft(testBST_B.root.right.right, 8);
		
		System.out.println("Testing BFS on BST:");
		BinaryTree.printNodesBFS(testBST_B.root);
		
		
		BinaryTree testBST_D = new BinaryTree();
		testBST_D.setRoot(6);
		
		testBST_D.addLeft(testBST_D.root, 2);
		testBST_D.addRight(testBST_D.root, 7);
		
		testBST_D.addLeft(testBST_D.root.left, 1);
		testBST_D.addRight(testBST_D.root.left, 4);
		testBST_D.addRight(testBST_D.root.right, 9);
		
		testBST_D.addLeft(testBST_D.root.left.right, 3);
		testBST_D.addRight(testBST_D.root.left.right, 5);
		testBST_D.addLeft(testBST_D.root.right.right, 8);
		
		
		System.out.println("Testing DFS on BST:");
		BinaryTree.printNodesDFS(testBST_D.root);
		
		
//		Graph testG = new Graph();
	}

}
