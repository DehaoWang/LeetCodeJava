package com.leetcodejava.algorithms.lc461;

/**
 * Created by wangdehao on 5/11/16.
 */
public class Main {
    public static int MAX_BINARY_BIT = 8;
    public static void main(String[] args){

        System.out.println("Leetcode Problem 461");

        int testNum1 = 4;
        int testNum2 = 1;

        int[] binary1 = convertNum2Binary(testNum1);
        int[] binary2 = convertNum2Binary(testNum2);

        printBinary(binary1);
        printBinary(binary2);

        System.out.println(diffBinaries(binary1, binary2));
        System.out.println(hammingDistance(testNum1, testNum2));
        System.out.println(hammingDistance2(testNum1, testNum2));
    }

    public static int[] convertNum2Binary(int num){
        int[] binary = new int[MAX_BINARY_BIT];
        int index = MAX_BINARY_BIT-1;
        for(int i = num; i > 0; i /= 2){
            binary[index] = i%2;
            index--;
        }
        return binary;
    }

    public static void printBinary(int[] binary){
        if(binary.length == 0 || binary == null){
            return;
        }
        for(int i = 0; i < binary.length; i++){
            System.out.print(binary[i]);
        }
        System.out.println();
    }

    public static int diffBinaries(int[] b1, int[] b2){
        int diffCount = 0;
        for(int i = 0; i < MAX_BINARY_BIT; i++){
            if(b1[i] != b2[i]){
                diffCount++;
            }
        }
        return diffCount;
    }

    public static int hammingDistance(int x, int y){
        int[] bx = convertNum2Binary(x);
        int[] by = convertNum2Binary(y);
        return diffBinaries(bx, by);
    }

    public static int hammingDistance2(int x, int y){
        int diffCount = 0;
        for(int i = 0; i < MAX_BINARY_BIT; i++){
            if(x%2 != y%2){
                diffCount++;
            }
            x /= 2;
            y /= 2;
        }
        return diffCount;
    }
}
