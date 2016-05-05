package com.leetcodejava.utils.LC36;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		char[][] testB = new char[9][9];
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				testB[i][j] = '.';
			}
		}


	}
	
	public boolean isValidSudoku(char[][] board)
	{
		boolean[] used;
		for(int k = 0; k < 9; k++)
		{
			used = new boolean[9];
			for(int j = 0; j < 9; j++)
			{
				if( ! check(board[k][j], used))
				{
					return false;
				}
			}
			used = new boolean[9];
			for(int i = 0; i < 9; i++)
			{
				if( ! check(board[i][k], used))
				{
					return false;
				}
			}
		}
		
		for(int r = 0; r < 3; r++)
		{
			for(int c = 0; c < 3; c++)
			{
				used = new boolean[9];
				for(int i = 3 * r; i < 3 * r + 3; i++)
				{
					for(int j = 3 * c; j < 3 * c + 3; j++)
					{
						if( ! check(board[i][j], used))
						{
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	public boolean check(char n, boolean[] u)
	{
		if(n == '.')
		{
			return true;
		}
		else if(u[n - '1'] == false)
		{
			u[n - '1'] = true;
			return true;
		}
		else
		{
			return false;
		}
		
	}
}
