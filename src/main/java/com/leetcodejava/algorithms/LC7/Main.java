package com.leetcodejava.algorithms.LC7;
import java.util.Stack;


public class Main {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		int testPI = 123;
		System.out.println("Reversed integer: " + revNum(testPI));
		System.out.println("R: " + rN(testPI));
		int testPN = -123;
		System.out.println("Reversed integer: " + revNum(testPN));
		System.out.println("R: " + rN(testPN));
		
		int testIE = 120;
		System.out.println("Reversed integer: " + revNum(testIE));
		System.out.println("R: " + rN(testIE));


		int testIE1 = 1534236469;
		System.out.println("Reversed integer: " + revNum(testIE1));
		System.out.println("R: " + rN(testIE1));
//1534236469

		double x = Math.pow(2, 31);
		System.out.println("x="+x);

	}

	public static int revNum(int num)
	{
		if(num == 0)
		{
			return 0;
		}
		else if(num > 0)
		{
			int numR = 0;
			Stack<Integer> s = new Stack<Integer>();
			while(num != 0)
			{
				s.push(num % 10);
				num = num / 10;
			}
			int size = s.size();
			for(int i = 0; i < size; i++)
			{
				numR += (int)s.pop() * Math.pow(10, i);
			}
			return numR;
		}
		else
		{
			return (-1) * revNum((-1) * num);
		}
	}
	
	// concise !
	public static int rN(int x)
	{
		int r = 0;

		for(; x != 0; x /= 10)
		{
			r = r * 10 + x % 10;
		}
        if(r >= 2.147483648E9 || r < -2.147483648E9){
            return 0;
        }
		return r;
	}
}
