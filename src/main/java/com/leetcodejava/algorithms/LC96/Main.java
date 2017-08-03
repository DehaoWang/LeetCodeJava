package com.leetcodejava.algorithms.LC96;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for(int i = 0; i < 5; i++)
		{
			System.out.println(utn(i));
		}
		
	}

	public static int utn(int n)
	{
		if(n == 0 || n == 1)
		{
			return 1;
		}
		else
		{
			int num = 0;
			for(int i = 0; i < n; i++)
			{
				num += utn(i) * utn(n - 1 - i);
			}
			return num;
		}
	}
}
