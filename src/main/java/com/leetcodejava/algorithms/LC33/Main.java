package com.leetcodejava.algorithms.LC33;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// back to normal
		
		int[] testSA = {1, 2, 3, 4, 5, 6, 7};
		System.out.println(binarySearchInSA(testSA, testSA.length, 6));
		
		int[] testRSA = {4, 5, 6, 7, 1, 2, 3};
		System.out.println(binarySearchInRSA(testRSA, testSA.length, 3));
		
	}

	public static int binarySearchInSA(int[] a, int n, int target)
	{
		int first = 0;
		int last = n - 1;
		
		while(first <= last)
		{
			int mid = (first + last) / 2;
			if(target < a[mid])
			{
				last = mid - 1;
			}
			else if(target > a[mid])
			{
				first = mid + 1;
			}
			else
			{
				return mid;
			}
		}
		return -1;
	}
	
	public static int binarySearchInRSA(int[] a, int n, int target)
	{
		int first = 0; 
		int last = n - 1;
		
		while(first <= last)
		{
			int mid = (first + last) / 2;
			if(target == a[mid])
			{
				return mid;
			}
			if(a[first] < a[mid])
			{
				if(target >= a[first] && target < a[mid])
				{
					last = mid - 1;
				}
				else
				{
					first = mid + 1;
				}
			}
			else
			{
				if(target > a[mid] && target <= a[last])
				{
					first = mid + 1;
				}
				else
				{
					last = mid - 1;
				}
			}
		}
		
		return -1;
	}
}
