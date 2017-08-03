package com.leetcodejava.algorithms.LC20;

import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		StackByArray testS = new StackByArray(10);
		StackByLinkedList testS = new StackByLinkedList();
		 
		testS.push('4');
		testS.push('5');
		testS.push('9');
		
		System.out.println(testS.pop());
		System.out.println(testS.pop());
		System.out.println(testS.pop());
		System.out.println(testS.pop());
		
		
		String testS1 = "{[]()}";
		String testS2 = "{[])}";
		String testS3 = "{{{[]}";
		
		// Self-implemented Stack by Array
		System.out.println(isValidParenthesesAS(testS1));
		System.out.println(isValidParenthesesAS(testS2));
		System.out.println(isValidParenthesesAS(testS3));
		
		// Ordinary Stack
		System.out.println(isValidParenthesesOS(testS1));
		System.out.println(isValidParenthesesOS(testS2));
		System.out.println(isValidParenthesesOS(testS3));
		
		// Self-implemented Stack by Linked List
		System.out.println(isValidParenthesesOS(testS1));
		System.out.println(isValidParenthesesOS(testS2));
		System.out.println(isValidParenthesesOS(testS3));
	}

	public static boolean isValidParenthesesOS(String str)
	{
		Stack<Character> sta = new Stack<Character>();
		for(int i = 0; i < str.length(); i++)
		{
			if(isLeftBracket(str.charAt(i)))
			{
				sta.push(str.charAt(i));
			}
			else if(isRightBracket(str.charAt(i)))
			{
				if( ! lrMatch((char)sta.pop(), str.charAt(i)))
				{
					return false;
				}
			}
		}
		if(sta.size() != 0)
		{
			return false;
		}
		
		return true;
	}
	
	public static boolean isValidParenthesesLLS(String str)
	{
		StackByLinkedList sta = new StackByLinkedList();
		for(int i = 0; i < str.length(); i++)
		{
			if(isLeftBracket(str.charAt(i)))
			{
				sta.push(str.charAt(i));
			}
			else if(isRightBracket(str.charAt(i)))
			{
				if( ! lrMatch((char)sta.pop(), str.charAt(i)))
				{
					return false;
				}
			}
		}
		if(sta.size() != 0)
		{
			return false;
		}
		
		return true;
	}
	
	public static boolean isValidParenthesesAS(String str)
	{
		StackByArray sta = new StackByArray(10);
		for(int i = 0; i < str.length(); i++)
		{
			if(isLeftBracket(str.charAt(i)))
			{
				sta.push(str.charAt(i));
			}
			else if(isRightBracket(str.charAt(i)))
			{
				if( ! lrMatch(sta.pop(), str.charAt(i)))
				{
					return false;
				}
			}
		}
		if(sta.size() != 0)
		{
			return false;
		}
		
		return true;
	}
	
	public static boolean isLeftBracket(char c)
	{
		if(c == '{' || c == '[' || c == '(')
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isRightBracket(char c)
	{
		if(c == '}' || c == ']' || c == ')')
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean lrMatch(char cl, char cr)
	{
		if(cl == '{' && cr == '}')
		{
			return true;
		}
		else if(cl == '[' && cr == ']')
		{
			return true;
		}
		else if(cl == '(' && cr == ')')
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
