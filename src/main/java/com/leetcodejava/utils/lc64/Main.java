package com.leetcodejava.utils.lc64;

public class Main 
{
	public static void main(String[] args)
	{
//		for(int i = 0; ;i++)
//		System.out.println(args[i]);
		
		
		int[][] testA = {{1, 2, 3, 2}, {2, 3, 1, 1}, {2, 1, 4, 3}, {2, 3, 1, 2}};
		int[][] testMPS = getMinimumPathSum_DP(testA);
		System.out.println(testMPS[0][0]);
		printMatrix(testMPS);
		
		
	}
	
	public static int[][] getMinimumPathSum_DP(int[][] a)
	{
		int r = a.length;
		int c = a[0].length;
		int[][] mps = new int[r][c];
		for(int i = r - 1; i >= 0; i--)
		{
			for(int j = c - 1; j >= 0; j--)
			{
				if(i == r - 1 && j == c - 1)
				{
					mps[i][j] = a[i][j];
				}
				else if(i == r - 1)
				{
					mps[i][j] = a[i][j] + mps[i][j + 1];
				}
				else if(j == c - 1)
				{
					mps[i][j] = a[i][j] + mps[i + 1][j];
				}
				else
				{
					mps[i][j] = a[i][j] + Math.min(mps[i][j + 1], mps[i + 1][j]);
				}
			}
		}
		return mps;
	}
	
	
	public static int[][] getMinimumPathSum_Memo(int[][] a)
	{
		return null;
	}
	
	
	public static void printMatrix(int[][] a)
	{
		for(int i = 0; i < a.length; i++)	
		{
			for(int j = 0; j < a[0].length; j++)
			{
				System.out.print(String.format("%5d", a[i][j]));
			}
			System.out.println();
		}
	}
}
