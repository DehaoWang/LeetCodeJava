package com.leetcodejava.utils.LC62;

import java.util.Timer;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i = 19;
		for(int n = 0; n < i; n++) {
			long timedpmemo1 = System.currentTimeMillis();
			Memo testM = new Memo(n, n);
			System.out.print(testM.dfs(n, n)+" ");
			long timedpmemo2 = System.currentTimeMillis();
			System.out.println("MEMO DP Time used: " + (timedpmemo2 - timedpmemo1));


			//testM.printMatrix();
			long timedp1 = System.currentTimeMillis();
			System.out.print(uniquePath(n, n)+" ");
			long timedp2 = System.currentTimeMillis();
			System.out.println("DP Time used: " + (timedp2 - timedp1));
		}
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
