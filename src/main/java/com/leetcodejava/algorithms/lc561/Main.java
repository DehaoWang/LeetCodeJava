package com.leetcodejava.algorithms.lc561;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        List<Integer> nums = Arrays.asList(1,2,3,4);
        int[] intNums = new int[nums.size()];
        for(int i = 0; i < nums.size(); i++){
            intNums[i] = nums.get(i);
        }

        int res = arrayPairSum(intNums);
        System.out.println(res);

	}

    // n*log(N)
    public static int arrayPairSum(int[] nums) {
        int n = nums.length / 2;
        int maxPairSum = 0;

        Arrays.sort(nums);
        for(int i = 0; i < n; i++){
            maxPairSum += nums[2 * i];
        }

        return maxPairSum;
    }
}
