package com.leetcodejava.utils.LC70;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(distinctWays(7));
	}

	public static int distinctWays(int n)
	{
		if(n == 1)
		{
			return 1;
		}
		else if(n == 2)
		{
			return 2;
		}
		else
		{
			int prev1 = 1;
			int prev2 = 2;
			int curr = 0;
			for(int i = 0; i < n - 2; i++)
			{
				curr = prev1 + prev2;
				prev1 = prev2;
				prev2 = curr;
			}
			return curr;
		}
	}
}
