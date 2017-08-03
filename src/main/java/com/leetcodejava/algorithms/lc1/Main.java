package com.leetcodejava.algorithms.lc1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.math3.distribution.NormalDistribution;

public class Main {

	public static void main(String[] args) throws ParseException {
        testQueue();

        NormalDistribution normalDistribution = new NormalDistribution();
        // p9 -> r1
        double probability = normalDistribution.cumulativeProbability(-0.4);
        System.out.println(probability);
        double mean = normalDistribution.getMean();
        System.out.println(mean);
        double deviation = normalDistribution.getStandardDeviation();
        System.out.println(deviation);

        double probability1 = normalDistribution.probability(-8, 8);
        System.out.println(probability1);

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

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("2016-07-19");
        Long time = date.getTime();
        System.out.println(time);
        Date date2 = simpleDateFormat.parse("2016-07-22");
        Long time2 = date2.getTime();
        System.out.println(time2);
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

    public static void testQueue() {
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i = 0; i < 15; i++){
            queue.add(i);
        }

        List<Integer> accountIds = new ArrayList<Integer>();

        Queue<List<Integer>> queueOfAccountIds = new LinkedList<List<Integer>>();
        while (queue.size() > 0) {
            Integer accountId = queue.poll();
            accountIds.add(accountId.intValue());
            if (accountIds.size() == 6) {
                queueOfAccountIds.add(accountIds);
                accountIds = new ArrayList<Integer>();
            }
        }
        queueOfAccountIds.add(accountIds);

        while(queueOfAccountIds.size() > 0){
            System.out.println(queueOfAccountIds.poll());
        }
    }
}

