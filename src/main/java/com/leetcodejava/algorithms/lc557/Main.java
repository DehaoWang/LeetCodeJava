package com.leetcodejava.algorithms.lc557;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        String testS = "Let's take LeetCode contest";
        String reverseS = reverseLine(testS);
        System.out.println(reverseS);

        System.out.println(reverseWords(testS));

        // faster
        System.out.println(reverseWords2(testS));

	}

    public static String reverseWords(String s) {

        StringBuffer stringBuffer = new StringBuffer();

        String[] tokens = s.split("[ ]+");
        for(int i = 0; i < tokens.length; i++){
            stringBuffer.append(tokens[tokens.length - 1 - i]);
            if(i < tokens.length - 1){
                stringBuffer.append(" ");
            }
        }

        String reverseStr = reverseLine(stringBuffer.toString());

        return reverseStr;
    }

    public static String reverseLine(String s){
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0; i < s.length(); i++){
            stringBuffer.append(s.charAt(s.length() - 1 - i));
        }
        return stringBuffer.toString();
    }

    public static void reverseWord(char[] word, int begin, int end){
        while (begin < end){
            char temp = word[begin];
            word[begin] = word[end];
            word[end] = temp;
            begin++;
            end--;
        }
    }

    public static String reverseWords2(String s){
        char[] words = s.toCharArray();

        int start = 0;
        for(int i = 0; i < words.length; i++){
            if(words[i] == ' '){
                reverseWord(words, start, i - 1);
                start = i + 1;
            }
        }
        reverseWord(words, start, words.length - 1);
        return new String(words);
    }
}
