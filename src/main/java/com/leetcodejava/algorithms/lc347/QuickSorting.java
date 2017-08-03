
package com.leetcodejava.algorithms.lc347;
public class QuickSorting
{
	public void quickSort(int[] a, int l, int r)
	{
		if(l >= r)
		{
			return;
		}
		else
		{
			int i = l;
			int j = r;
			int pivot = a[i];
			while(i < j)
			{
				while(i < j && a[j] >= pivot)
				{
					j--;
				}
				a[i] = a[j];
				while(i < j && a[i] <= pivot)
				{
					i++;
				}
				a[j] = a[i];
			}
			a[i] = pivot;
			quickSort(a, l, i - 1);
			quickSort(a, i + 1, r);
		}
		
	}
	
}
