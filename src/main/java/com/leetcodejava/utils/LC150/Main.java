package com.leetcodejava.utils.LC150;

import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] testC = {"1", "2", "+", "3", "*"};
		String[] testC1 = {"4", "13", "5", "/", "-"};
		System.out.println(evalRPN(testC));
		System.out.println(evalRPN(testC1));
//		char c = '4';
//		int x = (int) c-'0' + 3;
//		System.out.println(x);
	}

//	public static int evalRPNSWITCH(String[] tokens)
//	{
//		
//		Stack<Integer> s = new Stack<Integer>();
////		StackByLinkedList s = new StackByLinkedList();
//		
//		int x = 0;
//		int y = 0;
//		for(String token: tokens)
//		{
//			System.out.println(token);
//			System.out.println(s.size());
//			switch(token)
//			{
//				case '+' : 
//				{
//					y = (int) s.pop() - '0';
//					x = (int) s.pop() - '0';
//					System.out.println(s.size());
//					s.push(x + y);
//					System.out.println("@@@");
//					System.out.println(s.size());
//				}	
//				case '-' : 
//				{
//					y = (int) s.pop() - '0';
//					x = (int) s.pop() - '0';
//					s.push(x - y);
//				}
//				case '*' : 
//				{
//					y = (int) s.pop() - '0';
//					x = (int) s.pop() - '0';
//					s.push(x * y);
//				}
//				case '/' : 
//				{
//					y = (int) s.pop() - '0';
//					x = (int) s.pop() - '0';
//					s.push(x / y);
//				}
//				
//			}
//			s.push((int) token);
//		}
//		System.out.println(s.size());
////		s.push(4);
//		int r = s.pop();
//		return r;
//	}
	
	public static int evalRPN(String[] tokens)
	{
		
		Stack<Integer> s = new Stack<Integer>();
//		StackByLinkedList s = new StackByLinkedList();
		
		int x = 0;
		int y = 0;
		for(String token: tokens)
		{
			char c = token.charAt(0);
			if(token.length() == 1 && (c == '+' || c == '-' || c == '*' || c == '/'))
			{
				y = s.pop();
				x = s.pop();
				
				if(c == '+')
				{
					s.push(x + y);
				}
				if(c == '-')
				{
					s.push(x - y);
				}
				if(c == '*')
				{
					s.push(x * y);
				}
				if(c == '/')
				{
					s.push(x / y);
				}
			}
			else
			{
				s.push(Integer.parseInt(token));
			}
		}
		return s.pop();
	}
}
