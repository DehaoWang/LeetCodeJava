package com.leetcodejava.utils.LC120;

public class Main {

	public final static int BIG_NUM = 100000;
	
	public static void main(String[] args) 
	{

		int[][] testA = {{2}, {3, 4}, {6, 5, 4}, {4, 1, 8, 3}};
//		System.out.println(testA.length);
//		System.out.println(testA[0].length);
		System.out.println(getMinimumPathSum_DP(testA));
	}
	
	public static int getMinimumPathSum_DP(int[][] a)
	{
//		int minPath = BIG_NUM;
		
		for(int i = a.length - 2; i >= 0; i--)
		{
			for(int j = 0; j <= i; j++)
			{
				a[i][j] += Math.min(a[i+1][j], a[i+1][j+1]);
			}
		}
		return a[0][0];
	}
	
}
