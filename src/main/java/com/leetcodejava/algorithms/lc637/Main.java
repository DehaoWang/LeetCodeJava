package com.leetcodejava.algorithms.lc637;
import com.leetcodejava.algorithms.LC102_BT.BinaryTree;

import java.util.List;

/**
 * Created by wangdehao on 17/8/9.
 */
public class Main {
    public static void main(String[] args){
        System.out.println("lc637");

        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = new BinaryTree.Node(3);
        binaryTree.addLeft(binaryTree.root, 9);
        binaryTree.addRight(binaryTree.root, 20);
        binaryTree.addLeft(binaryTree.root.right, 15);
        binaryTree.addRight(binaryTree.root.right, 7);

//        BinaryTree.printNodesBFS(binaryTree.root);
        List<Double> avgByLevel = BinaryTree.calAvgByLevel(binaryTree.root);
        for(int i = 0; i < avgByLevel.size(); i++){
            System.out.println(avgByLevel.get(i));
        }
    }
}
