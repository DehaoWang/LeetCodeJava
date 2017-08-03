package com.leetcodejava.algorithms.LC88;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] testA = {1, 2, 4, 6, 0, 0, 0, 0, 0};
		int[] testB = {3, 5, 9};
		
		mergeSortedArrays(testA, 4, testB, 3);
		
		for(int i = 0; i < 7; i++)
		{
			System.out.println(testA[i]);
		}
	}

	public static void mergeSortedArrays(int[] a, int m, int[] b, int n)
	{
		int ia = m - 1;
		int ib = n - 1;
		int ic = m + n - 1;
		
		while(ia >= 0 && ib >= 0)
		{
			a[ic--] = a[ia] > b[ib] ? a[ia--] : b[ib--];
		}
		while(ib >= 0)
		{
			a[ic--] = b[ib--];
		}
	}
}
