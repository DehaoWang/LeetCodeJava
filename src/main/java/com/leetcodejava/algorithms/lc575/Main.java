package com.leetcodejava.algorithms.lc575;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        List<Integer> nums = Arrays.asList(1,2,3,4,5,2,3,1);
        int[] intNums = new int[nums.size()];
        for(int i = 0; i < nums.size(); i++){
            intNums[i] = nums.get(i);
        }

        System.out.println(distributeCandies(intNums));

	}

    public static int distributeCandies(int[] candies) {
        Set<Integer> disSet = new HashSet<Integer>();

        for(int i = 0; i < candies.length; i++){
            disSet.add(candies[i]);
        }

        return Math.min(disSet.size(), candies.length / 2);
    }
}
