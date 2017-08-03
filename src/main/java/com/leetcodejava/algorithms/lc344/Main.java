package com.leetcodejava.algorithms.lc344;

/**
 * Created by wangdehao on 5/11/16.
 */
public class Main {
    public static void main(String[] args){
        System.out.println("Leetcode Problem 344");
        String test1 = "hello";
        System.out.println(reverseString(test1));
        String test2 = "leetcode";
        System.out.println(reverseString(test2));
    }
    public static String reverseString(String str){
        if(str == null){
            System.out.println("NULL INPUT");
            return null;
        }
        else{
            char[] chars = str.toCharArray();
            int i = 0;
            int j = chars.length - 1;
            while(i < j){
                //while(i < j && ! isVowel(chars[i])){
                //    i++;
                //}
                //while(i < j && ! isVowel(chars[j])){
                //    j--;
                //}
                // swap for reversion
                char tmp = chars[i];
                chars[i] = chars[j];
                chars[j] = tmp;
                i++;
                j--;
            }
            return String.valueOf(chars);
        }
    }
    //public static boolean isVowel(char c){
    //    switch (c){
    //        case 'a': return true;
    //        case 'e': return true;
    //        case 'i': return true;
    //        case 'o': return true;
    //        case 'u': return true;
    //        default: return false;
    //    }
    //}
}
