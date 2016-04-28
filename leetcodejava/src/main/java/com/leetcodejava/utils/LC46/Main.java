package com.leetcodejava.utils.LC46;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String testS = "1234";
		
		for(String permutation : getPermutationsRecursiveByHS(testS))
		{
			System.out.println(permutation);
		}
	}

	public static Set<String> getPermutationsRecursiveByHS(String str)
	{
		Set<String> permutations = new TreeSet<String>();
	
		if(str.length() == 1)
		{
			permutations.add(str);

		}
		else
		{
			for(int i = 0; i < str.length(); i++)
			{
				char c = str.charAt(i);
				String smallerStr = str.substring(0, i) + str.substring(i + 1);
				for(String smallerPer : getPermutationsRecursiveByHS(smallerStr))
				{
					permutations.add(c + smallerPer);
				}
			}
		}
		return permutations;
	}
}
