package com.leetcodejava.utils.LC62;

public class Memo 
{
	private int height;
	private int width;
	
	private int[][] memo;
	
	public Memo(int x, int y)
	{
		height = x;
		width = y;
		
		memo = new int[height + 1][width + 1];
	}
	
	public int dfs(int x, int y)
	{
//		printMatrix();
		if(x < 1 || y < 1)
		{
			return 0;
		}
		else if(x == 1 && y == 1)
		{
			return 1;
		}
		else
		{
			return getOrUpdate(x - 1, y) + getOrUpdate(x, y - 1);
		}
	}
	
	private int getOrUpdate(int x, int y)
	{
		if(memo[x][y] > 0)
		{
			return memo[x][y];
		}
		else
		{
			return memo[x][y] = dfs(x, y);
		}
	}
	
	public void printMatrix()
	{
		for(int i = 0; i < height + 1; i++)
		{
			for(int j = 0; j < width + 1; j++)
			{
				System.out.print(String.format("%-8d",  memo[i][j]));
			}
			System.out.println();
			System.out.println();
		}
	}
}
