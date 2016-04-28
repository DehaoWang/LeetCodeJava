package com.leetcodejava.utils.LC134;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] testGAS = {3, 4, 2, 4, 7, 5};
		int[] testCOST = {3, 3, 1, 7, 5, 1};
		
		System.out.println(indexFromeValidPathN2(testGAS, testCOST, 6));
		System.out.println(indexFromValidPathN(testGAS, testCOST, 6));
	}

	public static int indexFromeValidPathN2(int[] gas, int[] cost, int n)
	{
		for(int i = 0; i < n; i++)
		{
			int j = i;
			int sum = 0;
			do
			{
				sum += gas[j] - cost[j];
//				System.out.println(j);
				j = (j + 1) % n;
			}
			while(j != i && sum >= 0);
			if(j == i)
			{
				return i;
			}
		}
		return -1;
	}
	
	public static int indexFromValidPathN(int[] gas, int[] cost, int n)
	{
		int sum = 0;
		int total = 0;
		int j = 0;
		for(int i = 0; i < n; i++)
		{
			sum += gas[i] - cost[i];
			total += gas[i] - cost[i];
			if(sum < 0)
			{
				sum = 0;
				j = i + 1;
			}
		}
		return total >= 0 ? j : -1;
	}
}
