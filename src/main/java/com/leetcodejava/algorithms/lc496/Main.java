package com.leetcodejava.algorithms.lc496;

import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        List<Integer> nums = Arrays.asList(1,3,4,2);
        List<Integer> findNums = Arrays.asList(4,1,2);
        int[] intNums = new int[nums.size()];
        int[] findIntNums = new int[findNums.size()];
        for(int i = 0; i < nums.size(); i++){
            intNums[i] = nums.get(i);
            System.out.println("nums["+i+"]="+intNums[i]);
        }
        for(int i = 0; i < findNums.size(); i++){
            findIntNums[i] = findNums.get(i);
            System.out.println("findNums["+i+"]="+findIntNums[i]);
        }

        int[] res = nextGreaterElement(findIntNums, intNums);
        for(int i = 0; i < res.length; i++){
            System.out.println(res[i]);
        }
	}

    public static int[] nextGreaterElement(int[] findNums, int[] nums) {
        if(findNums.length > nums.length || findNums.length == 0 || nums.length == 0){
            return new int[0];
        }
        int[] res = new int[findNums.length];
        for(int i = 0; i < findNums.length; i++){
            res[i] = -1;
            boolean findTarget = false;
            boolean find = false;
            for(int j = 0; j < nums.length; j++){
                if(nums[j] == findNums[i]){
                    findTarget = true;
                }
                if(nums[j] > findNums[i] && !find && findTarget){
                    res[i] = nums[j];
                    find = true;
                }
            }
        }
        return res;
    }
}
