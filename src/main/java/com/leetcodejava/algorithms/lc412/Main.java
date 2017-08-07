package com.leetcodejava.algorithms.lc412;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangdehao on 5/11/16.
 */
public class Main {

    public static void main(String[] args){
        System.out.println("Leetcode Problem 412");

        List<String> fb = fastFizzBuzz(16);
        for(int i = 0; i < fb.size(); i++){
            System.out.println(fb.get(i));
        }

    }
    public static List<String> fizzBuzz(int n) {
        if(n <= 0){
            return new ArrayList<String>();
        }
        List<String> fbList = new ArrayList<String>();
        for(int i = 1; i <= n; i++){
            if(i % 3 == 0 && i % 5 == 0){
                fbList.add("FizzBuzz");
            }
            else if(i % 3 == 0){
                fbList.add("Fizz");
            }
            else if(i % 5 == 0){
                fbList.add("Buzz");
            }
            else {
                fbList.add(""+i);
            }
        }
        return fbList;
    }
    public static List<String> fastFizzBuzz(int n){
        return answer.subList(0, n);
    }

    static List<String> answer = new AbstractList<String>() {
        @Override
        public String get(int i) {
            i++;
            return i % 15 == 0 ? "FizzBuzz":
                   i % 5 == 0 ? "Buzz":
                   i % 3 == 0 ? "Fizz":
                   Integer.toString(i);
        }

        @Override
        public int size() {
            return Integer.MAX_VALUE;
        }
    };
}
