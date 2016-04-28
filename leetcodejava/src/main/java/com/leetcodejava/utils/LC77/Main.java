package com.leetcodejava.utils.LC77;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String testStr = "abcde";
		int testK = 2;
		
		for(String combination : getCombinationsRecursive(testStr, 2))
		{
			System.out.println(combination);
		}
		System.out.println(getCombinationNum(5, 2));
	}

	public static Set<String> getCombinationsRecursive(String str, int k)
	{
		Set<String> combinations = new TreeSet<String>();
		
		if(k == str.length())
		{
			combinations.add(str);
		}
		else if(k == 0)
		{
			combinations.add("");
		}
		else
		{
			
			char c = str.charAt(0);
			String smallerStr = str.substring(1);
			
			// with *    : call C(n-1, k-1)
			for(String smallerCom : getCombinationsRecursive(smallerStr, k - 1))
			{
				combinations.add(c + smallerCom);
			}
			
			// without * : call C(n-1, k)
			for(String smallerCom : getCombinationsRecursive(smallerStr, k))
			{
				combinations.add(smallerCom);
			}
		}
		return combinations;
	}
	
	// C(n, m)
	public static int getCombinationNum(int n, int m)
	{
		if(n < m)
		{
			return 0;
		}
		else if(n == m || m == 0)
		{
			return 1;
		}
		else
		{
			return getCombinationNum(n - 1, m) + getCombinationNum(n - 1, m - 1);
		}
	}
}
