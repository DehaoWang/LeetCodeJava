package com.leetcodejava.algorithms.lc125;

/*
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
*/

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String testStr1 = "mad789aM899";
		String testStr2 = "madamad";
		System.out.println(isPalindrome(testStr1));
		System.out.println(isPalindrome(testStr2));
	}

	public static boolean isPalindrome(String s)
	{
		if(s == null)
		{
			return true;
		}
		
		int i = 0;
		int j = s.length() - 1;
		while(i < j)
		{
			while( ! ((s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') || (s.charAt(i) >= 'a' && s.charAt(i) <= 'z')) )
			{
				i++;
			}
			while( ! ((s.charAt(j) >= 'A' && s.charAt(j) <= 'Z') || (s.charAt(j) >= 'a' && s.charAt(j) <= 'z')) )
			{
				j--;
			}
			int d = Math.abs(s.charAt(i) - s.charAt(j));
			if(d != 0 && d != 32)
			{
				return false;
			}
			else
			{
				i++;
				j--;
			}
		}
		
		return true;
	}
}
