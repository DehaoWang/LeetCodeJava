
package com.leetcodejava.utils.lc347;

public class SA_Main
{

	public static void main(String[] args) 
	{
		int[] testArrayH = {5, 4, 8, 3, 2, 1, 9, 0, 6, 7};
		int[] testArrayQ = {5, 4, 8, 3, 2, 1, 9, 0, 6, 7};
		int[] testArrayM = {5, 4, 8, 3, 2, 1, 9, 0, 6, 7};

		printArray(testArrayH);
		
		System.out.println("TEST HEAPSORTING");
		HeapSorting testHS = new HeapSorting();
		testHS.heapSort(testArrayH, testArrayH.length);
		printArray(testArrayH);

		System.out.println("TEST QUICKSORTING");
		QuickSorting testQS = new QuickSorting();
		testQS.quickSort(testArrayQ, 0, testArrayQ.length - 1);
		printArray(testArrayQ);
		
		System.out.println("TEST MERGESORTING");
		MergeSorting testMS = new MergeSorting(); 
		int[] m = testMS.mergeSort(testArrayM);
		printArray(m);
	}
	
	
	public static void printArray(int[] a)
	{
		for(int i = 0; i < a.length; i++)
		{
			System.out.print(a[i] + " ");
		}
		System.out.println();
		System.out.println("*************************************");
	}

}
