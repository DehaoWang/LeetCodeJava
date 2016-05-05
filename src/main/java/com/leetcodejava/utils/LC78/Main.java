package com.leetcodejava.utils.LC78;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] testA = {1, 2, 3, 4};
		
		Set<Set<Integer>> testSSI = getSubsetsIterative(testA);
		
		for(Set<Integer> testS : testSSI)
		{
			System.out.println("Printing sets: ");
			for(int testI : testS)
			{
				System.out.println(testI);
			}
			System.out.println("***");
		}
		
		
		
		
//		Set<Set<Integer>> testSSR = getSubsets(testA, 0);
//		for(Set<Integer> testS : testSS)
//		{
//			for(int testI : testS)
//			{
//				System.out.println(testI);
//			}
//			System.out.println("***");
//		}
	}

	
	// NOT WORKING
	
	public static Set<Set<Integer>> getSubsetsRecursive(int[] nums, int start)
	{
		Set<Set<Integer>> subsets = new HashSet<Set<Integer>>();
//		Set<Integer> s = new TreeSet<Integer>();
//		if(nums.length == 0)
//		{
//			return subsets;
//		}
		if(start == nums.length)
		{
			Set<Integer> s = new TreeSet<Integer>();
			subsets.add(s);
			return subsets;
		}
		else if(start == nums.length - 1)
		{
			Set<Integer> s = new TreeSet<Integer>();
			s.add(nums[nums.length - 1]);
			subsets.add(s);
			return subsets;
		}
		else
		{
			int first = nums[start];
		
			System.out.println("@@@ " + first);
			
			subsets.addAll(getSubsetsRecursive(nums, start + 1));
			
//			for(Set<Integer> s : getSubsets(nums, start + 1))
//			{
//				s.add(first);
//				subsets.add(s);
//			}
			return subsets;
		}	
	}


	public static Set<Set<Integer>> getSubsetsIterative(int[] nums)
	{
		Set<Set<Integer>> subsets = new HashSet<Set<Integer>>();
		int setNum = (int) Math.pow(2, nums.length);
		for(int i = 0; i < setNum; i++)
		{	
			Set<Integer> s = new TreeSet<Integer>();
			int k = i;
			for(int j = 0; j < nums.length; j++)
			{
				if(k % 2 != 0)
				{
					s.add(nums[j]);
				}
				k = k / 2;
			}
			subsets.add(s);
		}
		return subsets;
	}
}
