package com.leetcodejava.algorithms.lc566;

import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        int[][] matrix = new int[][]{{0,1,2},{3,4,5},{6,7,8}};

        printMatrix(matrix);

        printMatrix(matrixReshape(matrix, 2, 4));
	}

    public static int[][] matrixReshape(int[][] nums, int r, int c) {
        int width = nums[0].length;

        if(r * c > nums.length * width){
            return nums;
        }
        int[][] reshapedMatrix = new int[r][c];
//        for(int i = 0 ;i < r; i++){
//            for(int j = 0; j < c; j++){
//                int loc = i * c + j;
//                reshapedMatrix[i][j] = nums[loc/width][loc%width];
//            }
//        }

        // better Solution
        for(int i = 0 ;i < r*c; i++){
            reshapedMatrix[i/c][i%c] = nums[i/width][i%width];
        }

        return reshapedMatrix;
    }

    public static void printMatrix(int[][] nums){
        for (int row = 0; row < nums.length; row ++){
            for (int col = 0; col < nums[0].length; col++){
                System.out.print(nums[row][col] + " ");
            }
            System.out.println();
        }
    }
}
