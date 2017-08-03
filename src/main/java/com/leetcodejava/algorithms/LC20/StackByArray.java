package com.leetcodejava.algorithms.LC20;



public class StackByArray 
{
	char[] arrayStack;
	int top;
	
	public StackByArray(int cap)
	{
		arrayStack = new char[cap];
		top = -1;
//		System.out.println(arrayStack[5]);
	}
	
	public void push(char c)
	{
		arrayStack[++top] = c;
 	}
	
	public char pop()
	{
		if(top == -1)
		{
			return '0';
		}
		else
		{
			return arrayStack[top--];
		}
	}
	
	public char peek()
	{
		if(top == -1)
		{
			return '0';
		}
		else
		{
			return arrayStack[top];
		}
	}
	
	public int size()
	{
		return top + 1;
	}
}
