package com.leetcodejava.algorithms.lc463;

/**
 * Created by wangdehao on 5/11/16.
 */
public class Main {

    public static void main(String[] args){
        System.out.println("Leetcode Problem 463");
        int[][] matrix = new int[][]{{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        int[][] matrix1 = new int[][]{{1}};
        int[][] matrix2 = new int[][]{{1,1}};
        System.out.println(islandPerimeter(matrix));
        System.out.println(islandPerimeter(matrix1));
        System.out.println(islandPerimeter(matrix2));
    }

    public static int islandPerimeter(int[][] grid) {
        int adjCount = 0;
        int oneCount = 0;
        int rowLen = grid.length;
        int colLen = grid[0].length;
        if(rowLen == 1 && colLen == 1 && grid[0][0] == 1){
            return 4;
        }
        for(int i = 0; i < rowLen; i++){
            for(int j = 0; j < colLen; j++){
                oneCount += grid[i][j];
            }
        }
        if(rowLen == 1 && colLen > 1){
            for(int j = 0; j < colLen-1; j++){
                int k = j+1;
                if(grid[0][j] == 1 && grid[0][k] == 1){
                    adjCount++;
                }
            }
        }
        if(rowLen > 1 && colLen == 1){
            for(int i = 0; i < rowLen-1; i++){
                int k = i+1;
                if(grid[i][0] == 1 && grid[k][0] == 1){
                    adjCount++;
                }
            }
        }
        if(rowLen > 1 && colLen > 1){
            for(int i = 0; i < rowLen; i++){
                for(int j = 0; j < colLen-1; j++){
                    int k = j+1;
                    if(grid[i][j] == 1 && grid[i][k] == 1){
                        adjCount++;
                    }
                }
            }
            for(int j = 0; j < colLen; j++){
                for(int i = 0; i < rowLen-1; i++){
                    int k = i+1;
                    if(grid[i][j] == 1 && grid[k][j] == 1){
                        adjCount++;
                    }
                }
            }
        }
        return 4 * oneCount - 2 * adjCount;
    }


}
