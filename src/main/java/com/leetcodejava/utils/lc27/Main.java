package com.leetcodejava.utils.lc27;

/*
Given an array and a value, remove all instances of that value in place and return the new length.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.
*/
public class Main {
	public static void main(String[] args) {
		
		
		int[] testA = {1, 2, 3, 4, 5, 2, 6, 2};
		// testing the return value
		System.out.println("last index: " + removeElement(testA, 8, 2));
		// testing the modified array
		for(int k = 0; k < testA.length; k++)
		{
			System.out.println(testA[k]);
		}
		
		System.out.println("*************");
		
		// testing method No.2
		int[] testB = {1, 2, 3, 4, 5, 2, 6, 2};
		
		System.out.println("last index: " + rD(testB, 8, 2));
		
		for(int k = 0; k < testB.length; k++)
		{
			System.out.println(testB[k]);
		}
	}

	// 
	public static int removeElement(int[] a, int n, int target)
	{
		if(n == 0)
		{
			return 0;
		}
		else
		{
			int i = 0;
			int j = n - 1;
			while(i < j)
			{
				while(i < j && a[i] != target)
				{
					i++;
				}
				while(i < j && a[j] == target)
				{
					j--;
				}
				int temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			}	
			return i;
		}
	}
	
	// method No.2
	public static int rD(int[] a, int n, int elem)
	{
		if(n == 0)
		{
			return 0;
		}
		else
		{
			int index = 0;
			for(int i = 0; i < n; i++)
			{
				if(a[i] != elem)
				{
					a[index++] = a[i];
				}
			}
			return index;
		}
	}
}
