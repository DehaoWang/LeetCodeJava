package com.leetcodejava.utils.LC46;

import com.sun.javafx.binding.StringFormatter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//String testS = "1234";
		//
		//for(String permutation : getPermutationsRecursiveByHS(testS))
		//{
		//	System.out.println(permutation);
		//}


		// phase 1: subs generator

		String testS2 = "abcde";

		Set<String> permutationsSet = getPermutationsOpt(testS2);

		//for(String permutationExt : permutationsSet){
		//	System.out.println(permutationExt);
		//}
		System.out.println(permutationsSet.size());

		// phase 2: match count
		String model = "abc";
		String target = "ababcacbaabcc";
		for(String key: getPermutationsOpt(model)){
			System.out.println(key + " / " + subCount(target, key));
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

	public static Set<String> getPermutationsExt(String str){

		Set<String> permutationsExt = new TreeSet<String>();

		if(str.length() == 0){
			permutationsExt.add("");
		}
		else{

			char c = str.charAt(0);

			String smallerStr = str.substring(1);

			for(String eachSmallerPermutation: getPermutationsExt(smallerStr)){
				permutationsExt.add(eachSmallerPermutation);
				for(String strAfterInsert: insertIntoEachPostion(eachSmallerPermutation, c)){
					permutationsExt.add(strAfterInsert);
				}
			}
		}



		return permutationsExt;
	}

	public static Set<String> insertIntoEachPostion(String str, char c){
		Set<String> strs = new TreeSet<String>();
		for(int i = 0; i <= str.length(); i++){
			StringBuffer sb = new StringBuffer(str);
			sb.insert(i, c);
			strs.add(sb.toString());
		}
		return strs;
	}

	public static Set<String> getPermutationsOpt(String str){
		Set<String> permutationsOpt = new TreeSet<String>();
		if(str.length() == 0){
			permutationsOpt.add("");
		}
		else {
			for(int i = 0; i < str.length(); i++){
				char c = str.charAt(i);
				String smallerStr = str.substring(0, i) + str.substring(i + 1);
				for(String eachSmallerPermutation: getPermutationsExt(smallerStr)){
					permutationsOpt.add(c + eachSmallerPermutation);
				}
			}

		}
		return permutationsOpt;
	}


	public static int subCount(String string, String subs){
		int count = 0;
		String temp = string;
		int index = temp.indexOf(subs);
		while(index != -1){
			count++;
			// update
			temp = temp.substring(index + subs.length());
			index = temp.indexOf(subs);
		}
		return count;
	}
}
