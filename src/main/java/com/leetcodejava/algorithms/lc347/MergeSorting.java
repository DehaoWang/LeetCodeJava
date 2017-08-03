
package com.leetcodejava.algorithms.lc347;

public class MergeSorting 
{	
	public int[] mergeSort(int[] a)
	{
		if(a.length == 1)
		{
			return a;
		}

		{
			int mid = a.length / 2;
			int[] leftArray = new int[mid];
			int[] rightArray = new int[a.length - mid];
			System.arraycopy(a, 0, leftArray, 0, mid);
			System.arraycopy(a, mid, rightArray, 0, a.length - mid);

			leftArray = mergeSort(leftArray);
			rightArray = mergeSort(rightArray);
			
			return mergeArrays(leftArray, rightArray);
		}
	}
	
	public int[] mergeArrays(int[] a1, int[] a2)
	{
		int[] a = new int[a1.length + a2.length];
		int i = 0;
		int j = 0;
		for(int k = 0; k < a1.length + a2.length; k++)
		{
			if(i < a1.length && j < a2.length)
			{
				if(a1[i] < a2[j])
				{
					a[k] = a1[i];
					i++;
				}	
				else
				{
					a[k] = a2[j];
					j++;
				}
			}
			else if(i == a1.length)
			{
				a[k] = a2[j];
				j++;
			}
			else if(j == a2.length)
			{
				a[k] = a1[i];
				i++;
			}
		}
		
		
		return a;
	}
}
