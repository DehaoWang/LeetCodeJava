package com.leetcodejava.utils.lc347;

public class HeapSorting 
{

	
	public void heapAdjust(int[] a, int s, int l)
	{
		int i = s;
		int j = 2 * i + 1;
		int temp = a[i];
		
		// adjust heap from top to bottom
		while(j < l)
		{
			if(j < l - 1 && a[j] < a[j + 1])
			{
				j++;
			}
			if(a[i] > a[j])
			{
				break;
			}
			else
			{
				a[i] = a[j];
				i = j;
				j = 2 * i + 1;
				a[i] = temp;
			}
			
		}
	}
	
	public void buildMaxHeap(int[] a, int l)
	{
		// build heap from bottom to top
		for(int i = l/2 - 1; i >= 0; i--)
		{
			heapAdjust(a, i, l);
		}
	}
	
	public void heapSort(int[] a, int l)
	{
		for(int i = 0; i < l; i++)
		{				
			buildMaxHeap(a, l - i);
			// swap
			int temp = a[0];
			a[0] = a[l - 1 - i];
			a[l - 1 - i] = temp;
		}		
	}
}
