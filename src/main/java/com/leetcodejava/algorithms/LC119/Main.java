package com.leetcodejava.algorithms.LC119;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] testKR = generateKthRow(5);
		for(int i = 0; i < 5; i++)
		{
			System.out.print(testKR[i] + " ");
		} 
	}

	public static int[] generateKthRow(int k)
	{
		int[] kr = new int[k];
		for(int i = 0; i < k; i++)
		{
			for(int j = i; j >= 0; j--)
			{
				if(j == i || j == 0)
				{
					kr[j] = 1;
				}
				else
				{
					kr[j] = kr[j] + kr[j - 1];
				}
			}
		}
		
		return kr;
	}
}
