package com.leetcodejava.algorithms.lc63;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		System.out.println(uniquePath(30, 30));
		
		
		int[][] testOG = new int[3][3]; 
		testOG[0][1] = 1;
		
		
		Memo testM = new Memo(3, 3, testOG);
		
		
		
		System.out.println(testM.dfs(3, 3));
		
		testM.printMatrix();
	}

	public static int uniquePath(int m, int n)
	{
		if(m < 1 || n < 1)
		{
			return 0;
		}
		else if(m == 1 && n == 1)
		{
			return 1;
		}
		else
		{
			return uniquePath(m - 1, n) + uniquePath(m, n - 1);
		}
	}
}
