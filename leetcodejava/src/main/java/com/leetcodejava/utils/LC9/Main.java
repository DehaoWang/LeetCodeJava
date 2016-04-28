package com.leetcodejava.utils.LC9;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isPalindromeNumber(1233421));
	}

	public static boolean isPalindromeNumber(int x)
	{
		int d = 1;
		while(x / d >= 10)
		{
			d *= 10;
		}
		
		while(x > 0)
		{
			int q = x / d;
			int r = x % 10;
			if(q != r)
			{
				return false;
			}
			else
			{
				x = x % d / 10;
				d /= 100;
			}

		}
		
		return true;
	}
}
