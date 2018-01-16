package com.leetcodejava.algorithms.lintShareMax;

import org.omg.CORBA.INTERNAL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by wangdehao on 17/8/9.
 */
public class Main {
    public static void main(String[] args){
//        System.out.println("lintShareMax");
//
//        List<Integer> shareLogs = Arrays.asList(2, 4, 5, 1, 3, 6, 3, 5, 5, 0, 9);
//        int[] shareLogsArray = getArrayFromList(shareLogs);
//
//        System.out.println(getMaxShareN2(shareLogsArray));
//        System.out.println(getMaxShareN(shareLogsArray));
//
//        getRmseFromFile();

        analysisStayCorrel();
    }

    private static int[] getArrayFromList(List<Integer> shareLogs) {
        int size = shareLogs.size();
        int[] array = new int[size];
        for(int i = 0; i < size; i++){
            array[i] = shareLogs.get(i);
        }
        return array;
    }

    private static int getMaxShareN2(int[] shareLogsArray) {
        int max = 0;
        for(int i = 0; i < shareLogsArray.length - 1; i++){
            for(int j = i + 1; j < shareLogsArray.length; j++){
                if(max < shareLogsArray[j] - shareLogsArray[i]){
                    max = shareLogsArray[j] - shareLogsArray[i];
                }
            }
        }
        return max;
    }

    private static int getMaxShareN(int[] shareLogsArray) {
        int max = 0;
        int currMax = 0;
        for(int i = 0; i < shareLogsArray.length - 1; i++){
            int diff = shareLogsArray[i + 1] - shareLogsArray[i];
            currMax += diff;
            if(currMax > max){
                max = currMax;
            }
            if(currMax < 0){
                currMax = 0;
            }
        }
        return max;
    }

    public static void getRmseFromFile(){
        double sum = 0.0;
        int cnt = 0;
        String fileName = "/Users/wangdehao/ADP_Projects/data/prodData/dataEva";
        File file = new File(fileName);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String[] tokens = line.trim().split("[,()]+");
                Double a = Double.parseDouble(tokens[1]);
                Double b = Double.parseDouble(tokens[2]);
                sum += (a-b)*(a-b);
                cnt++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(sum);

        double rmse = Math.sqrt(sum/cnt);
        System.out.println(rmse);

    }

    public static void analysisStayCorrel(){
        String fileName = "/Users/wangdehao/ADP_Projects/data/prodData/stayAnalysis/stayAnaBu1117";
        File file = new File(fileName);
        List<ShopProfile> shopProfiles = new ArrayList<ShopProfile>();
        List<SingleEffectProfile> cliProfiles = new ArrayList<SingleEffectProfile>();
        List<SingleEffectProfile> impProfiles = new ArrayList<SingleEffectProfile>();
        List<SingleEffectProfile> roiProfiles = new ArrayList<SingleEffectProfile>();
        int count = 0;
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){
                String line = sc.nextLine();
//                System.out.println(line);
                count++;
                if(count <= 1){
                    continue;
                }
                String[] tokens = line.trim().split("[\t]+");
                Integer shopId = Integer.parseInt(tokens[0]);
                Integer onlineCli = Integer.parseInt(tokens[1]);
                Integer onlineImp = Integer.parseInt(tokens[2]);
                Double avgRoi = Double.parseDouble(tokens[3]);
                Integer isStay = Integer.parseInt(tokens[4]);
                Integer buType = Integer.parseInt(tokens[5]);
                ShopProfile shopProfile = new ShopProfile(shopId,onlineCli,onlineImp,avgRoi,isStay);
                SingleEffectProfile cliProfile = new SingleEffectProfile(shopId,(double)onlineCli,isStay);
                SingleEffectProfile impProfile = new SingleEffectProfile(shopId,(double)onlineImp,isStay);
                SingleEffectProfile roiProfile = new SingleEffectProfile(shopId,avgRoi,isStay);
                cliProfiles.add(cliProfile);
                impProfiles.add(impProfile);
                roiProfiles.add(roiProfile);
                shopProfiles.add(shopProfile);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(shopProfiles.size());
        System.out.println(count);

        // phase SortByEffect
        sortByEffect(cliProfiles);
        sortByEffect(impProfiles);
        sortByEffect(roiProfiles);

        // phase IntervalStayRatio
//        intervalStayRatio(cliProfiles, 100);
//        intervalStayRatio(impProfiles, 100);
        intervalStayRatio(roiProfiles, 100);
    }

    public static void sortByEffect(List<SingleEffectProfile> singleEffectProfileList){
        Collections.sort(singleEffectProfileList, new Comparator<SingleEffectProfile>() {
            public int compare(SingleEffectProfile o1, SingleEffectProfile o2) {
                if(o1.effect < o2.effect){
                    return -1;
                }
                else if(o1.effect > o2.effect){
                    return 1;
                }
                return 0;
            }
        });
        System.out.println(singleEffectProfileList.get(singleEffectProfileList.size()-1).shopId);
    }

    public static void intervalStayRatio(List<SingleEffectProfile> singleEffectProfileList, int intervalNum){
        int size = singleEffectProfileList.size();
        int threshold = size / intervalNum;
        int intervalCount = 0;
        int stayCount = 0;
        int level = 0;

        double sum = 0.0;
        double mid = 0.0;
        double pivotMin = (double)Integer.MAX_VALUE;
        double min = pivotMin;
        double max = 0.0;

        for(int i = 0; i < size; i++){
            intervalCount++;
            SingleEffectProfile singleEffectProfile = singleEffectProfileList.get(i);
            double effect = singleEffectProfile.effect;
            sum += effect;

            if(effect < min){
                min = effect;
            }
            if(effect > max){
                max = effect;
            }

            if(singleEffectProfile.isStay == 1){
                stayCount++;
            }
            if(intervalCount == threshold/2){
                mid = effect;
            }
            if(intervalCount == threshold){
//                System.out.println("level "+level+" stayRatio= "+(double)stayCount/intervalCount+" avg= "+sum/intervalCount+" mid= "+mid+" intervalCount= "+intervalCount);
                System.out.println(level+","+(double)stayCount/intervalCount+","+sum/intervalCount+","+mid+","+min+","+max);
                intervalCount = 0;
                stayCount = 0;
                level++;
                sum = 0;
                min = pivotMin;
                max = 0;
                mid = 0;
            }
        }
        System.out.println("level "+level+" stayRatio= "+(double)stayCount/intervalCount+" avg= "+sum/intervalCount+" mid= "+mid+" intervalCount= "+intervalCount);
    }
}
