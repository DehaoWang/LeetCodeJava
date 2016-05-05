package com.leetcodejava.utils.LC118;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] testPT = generatePT(5);
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j <= i; j++)
			{
				System.out.print(testPT[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static int[][] generatePT(int k)
	{
		int[][] pt = new int[k][k];
		for(int i = 0; i < k; i++)
		{
			for(int j = 0; j <= i; j++)
			{
				if(j == 0 || j == i)
				{
					pt[i][j] = 1;
				}
				else
				{
					pt[i][j] = pt[i - 1][j - 1] + pt[i - 1][j];
				}
			}
		}
		return pt;
	}
}
