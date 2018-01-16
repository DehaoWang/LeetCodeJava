package com.leetcodejava.algorithms.lc521;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s1 = "aba";
		String s2 = "aba";


		System.out.println(findLUSlength(s1, s2));
	}


	public static int findLUSlength(String a, String b) {

		return -1;
	}

    public Set<String> getSubStrings(String s){
        Set<String> stringSet = new TreeSet<String>();

        char[] chars = s.toCharArray();
        for(int i = 0; i < Math.pow(2, s.length()); i++){

        }

        return stringSet;
    }
}
