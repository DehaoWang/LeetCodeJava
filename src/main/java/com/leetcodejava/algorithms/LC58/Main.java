package com.leetcodejava.algorithms.LC58;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String testS = "Hello World Guys";
		System.out.println(lengthOfLastWord(testS));
	}

	public static int lengthOfLastWord(String s)
	{
		int len = 0;
		
		for(int i = 0; i < s.length(); i++)
		{
			if( ! (s.charAt(i) == ' '))
			{
				len++;
			}
			else
			{
				len = 0;
			}
		}
		
		return len;
	}
}
