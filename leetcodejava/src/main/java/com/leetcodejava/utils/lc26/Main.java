package com.leetcodejava.utils.lc26;

/*
Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array A = [1,1,2],

Your function should return length = 2, and A is now [1,2].
*/

public class Main {

	public static void main(String[] args) 
	{
		int[] testA = {1, 1, 2, 3, 3, 4};
		
		// testing the return value
		int biggestIndex = removeDuplicates(testA);
		System.out.println(biggestIndex);
		
		// testing the sieved array
		for(int i = 0; i < biggestIndex; i++)
		{
			System.out.println(testA[i]);
		}
	}
	
	public static int removeDuplicates(int[] a)
	{
		if(a == null)
		{
			return 0;
		}
		else
		{
			int index = 0;
			for(int i = 0; i < a.length; i++)
			{
				if(a[i] != a[index])
				{
					a[++index] = a[i];
				}
			}	
			return index + 1;
		}
	}
}
