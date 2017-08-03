package com.leetcodejava.algorithms.LC136;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(0^6);
		System.out.println(6^6);
		int[] testA = {2, 3, 9, 3, 2, 4, 4};
		System.out.println(singleNumber(testA, 7));
	}

	// XOR
	public static int singleNumber(int[] a, int n)
	{
		if(a == null)
		{
			return -1;
		}
		else
		{
			int sn = 0;
			for(int i = 0; i < n; i++)
			{
				sn ^= a[i];
			}
			return sn;
		}
	}
}
