package lc1;
import java.util.Arrays;


public class Main {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		int[] testA = {2, 7, 11, 14};
		int testT = 18;

		
		int[] r0 = twoSumN2(testA, testT);
		System.out.println("index1 = " + r0[0]);
		System.out.println("index2 = " + r0[1]);
		
		System.out.println("***********************");
		
		int[] r1 = twoSumN(testA, testT);
		System.out.println("index1 = " + r1[0]);
		System.out.println("index2 = " + r1[1]);
	}

	public static int[] twoSumN2(int[] a, int target)
	{
		int[] indexes = new int[2];
		
		for(int i = 0; i < a.length; i++)
		{
			for(int j = i + 1; j < a.length; j++)
			{
				if(a[i] + a[j] == target)
				{
					indexes[0] = i + 1;
					indexes[1] = j + 1;
					break;
				}
			}
		}
		return indexes;
	}
	
	public static int[] twoSumN(int[] a, int target)
	{
		int[] indexes = new int[2];
		Arrays.sort(a);
		int i = 0;
		int j = a.length - 1;
		boolean found = false;
		while(i < j && !found)
		{
			if(a[i] + a[j] < target)
			{
				i++;
			}
			else if(a[i] + a[j] > target)
			{
				j--;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
			}
			else
			{
				indexes[0] = i + 1;
				indexes[1] = j + 1;
				found = true;
			}
		}		
		return indexes;
	}
	
	
	
	public static int[] twoSum(int[] a, int target)
	{
		int[] indexes = new int[2];
		
		return indexes;
	}

}

