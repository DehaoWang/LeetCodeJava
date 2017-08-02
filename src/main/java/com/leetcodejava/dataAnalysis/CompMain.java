package com.leetcodejava.dataAnalysis;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * Created by wangdehao on 16/12/22.
 */
public class CompMain
{
    public static final double THRESHOLD = 0.5;
    public static void main( String[] args ) throws FileNotFoundException {
//        System.out.println( "Hello World!" );
//        diffData("/Users/wangdehao/Documents/Files/KPI_GOAL/testDiff");

//        tearData("/Users/wangdehao/Documents/Files/tearData");
//        calEstStat("/Users/wangdehao/ADP_Projects/data/prodData/MLfiles/originPredictionData6Month");
//        evaluateML("/Users/wangdehao/ADP_Projects/data/prodData/MLfiles/Feb_Detail_SVM_Old");

        buildFeatureLabelMap("/Users/wangdehao/ADP_Projects/data/prodData/MLfiles/0401/prob1-0");
        dataCompare("/Users/wangdehao/ADP_Projects/data/prodData/MLfiles/0401/dataComp", 0, 1, "");
//        errEvaluate("/Users/wangdehao/ADP_Projects/data/prodData/MLfiles/0401/errEvaluation", 1);
//        errEvaluate2("/Users/wangdehao/ADP_Projects/data/prodData/MLfiles/0401/errEvaluation", 1);
        checkDistribution("/Users/wangdehao/ADP_Projects/data/prodData/MLfiles/0401/April_Origin");
//        checkDistribution("/Users/wangdehao/ADP_Projects/data/prodData/MLfiles/0401/April_ML_class2");

    }

    private static void checkDistribution(String fileName) throws FileNotFoundException {
        FileReader fileReader = new FileReader(fileName);
        Scanner scanner = new Scanner(fileReader);

        Map<Double, Integer> distributionMap = new HashMap<Double, Integer>();
        int num = 10;
        double[] breakPoints = new double[num];
        for(int i = 0; i < breakPoints.length; i++){
            breakPoints[i] = (double)(i)/num;
            distributionMap.put(breakPoints[i], 0);
//            System.out.println(breakPoints[i]);
        }
        distributionMap.put(1.0, 0);

        int count = 0;
        double sum = 0.0;
        int secEst = 0;
        while(scanner.hasNext()) {

            count++;

            String line = scanner.nextLine();
            Double prob = Double.parseDouble(line);


            sum+= prob;

            if(prob >= 0.2){
                secEst++;
            }

            for(int i = 0; i < breakPoints.length-1; i++){
//                if(breakPoints[i] > prob){
//
//                }
                if(breakPoints[i] <= prob && breakPoints[i+1] > prob){
                    distributionMap.put(breakPoints[i], distributionMap.get(breakPoints[i])+1);
                    continue;
                }
                else if(breakPoints[num-1] <= prob && prob < 1){
                    distributionMap.put(breakPoints[num-1], distributionMap.get(breakPoints[num-1])+1);
                }
//                else if(prob >0){
//                    distributionMap.put(1.0, distributionMap.get(1.0)+1);
//                }
            }
        }
        System.out.println(distributionMap);
        System.out.println(count);
        System.out.println(sum);
        System.out.println(sum/count);
        System.out.println(secEst);
    }

    private static void errEvaluate(String fileName, int minusMonth) throws FileNotFoundException {
        FileReader fileReader = new FileReader(fileName);
        Scanner scanner = new Scanner(fileReader);
        Map<String, CityNameCalOriMl> cityName2CalOriMl = new HashMap<String, CityNameCalOriMl>();
        int percent5ori = 0;
        int percent5ml = 0;
        int zeroOri = 0;
        while(scanner.hasNext()) {
            String line = scanner.nextLine();

            String[] tokens = line.split("[\t]+");
            int len = tokens.length;
            Long accountId = Long.parseLong(tokens[0]);
            Double cal = Double.parseDouble(tokens[minusMonth]);
            Double estOri = Double.parseDouble(tokens[minusMonth+1]);
            Double estML = Double.parseDouble(tokens[minusMonth+2]);
            String cityName = tokens[len-2];
            CityNameCalOriMl cityNameCalOriMl = cityName2CalOriMl.get(cityName);
            if(cityNameCalOriMl == null){
                cityNameCalOriMl = new CityNameCalOriMl();
                cityName2CalOriMl.put(cityName, cityNameCalOriMl);
            }
            if(cal > THRESHOLD){
                cityNameCalOriMl.addAccountId2Cal(accountId);
            }
            if(estOri > THRESHOLD){
                cityNameCalOriMl.addAccountId2Ori(accountId);
            }
            if(estML > THRESHOLD){
                cityNameCalOriMl.addAccountId2Ml(accountId);
            }

        }
        for(String cityName: cityName2CalOriMl.keySet()){
            CityNameCalOriMl cityNameCalOriMl = cityName2CalOriMl.get(cityName);
            if(cityNameCalOriMl.getCalRenewAids().size() == 0 && cityNameCalOriMl.getOriRenewAids().size() == 0){
                zeroOri++;
            }
            if(cityNameCalOriMl.oriBelowP5()){
                percent5ori++;
                System.out.println(cityName);
                System.out.println(cityName+" "+cityNameCalOriMl.getInfo());
            }
            if(cityNameCalOriMl.mlBelowP5()){
                percent5ml++;
            }
//            System.out.println(cityName+" "+cityNameCalOriMl.getInfo());
        }
        System.out.println("zeroOri: " + zeroOri);
        System.out.println("oriP5: " + percent5ori);
        System.out.println("mlP5: " + percent5ml);
    }

    private static void errEvaluate2(String fileName, int minusMonth) throws FileNotFoundException {
        FileReader fileReader = new FileReader(fileName);
        Scanner scanner = new Scanner(fileReader);
        Map<Integer, UserIdCalOriMl> userId2CalOriMl = new HashMap<Integer, UserIdCalOriMl>();
        while(scanner.hasNext()) {
            String line = scanner.nextLine();

            String[] tokens = line.split("[\t]+");
            int len = tokens.length;
//            System.out.println(tokens[len-1]);
            Long accountId = Long.parseLong(tokens[0]);
            Double cal = Double.parseDouble(tokens[minusMonth]);
            Double estOri = Double.parseDouble(tokens[minusMonth+1]);
            Double estML = Double.parseDouble(tokens[minusMonth+2]);
            Integer userId = Integer.parseInt(tokens[len-1]);
            UserIdCalOriMl userIdCalOriMl = userId2CalOriMl.get(userId);
            if(userIdCalOriMl == null){
                userIdCalOriMl = new UserIdCalOriMl();
                userId2CalOriMl.put(userId, userIdCalOriMl);
            }
            if(cal > THRESHOLD){
                userIdCalOriMl.addAccountId2Cal(accountId);
            }
            if(estOri > THRESHOLD){
                userIdCalOriMl.addAccountId2Ori(accountId);
            }
            if(estML > THRESHOLD){
                userIdCalOriMl.addAccountId2Ml(accountId);
            }

        }
        for(Integer userId: userId2CalOriMl.keySet()){
            UserIdCalOriMl userIdCalOriMl = userId2CalOriMl.get(userId);
            System.out.println(userId+" "+userIdCalOriMl.getInfo());
        }
    }

    public static void diffData(String fileName) throws FileNotFoundException {
        Set<Integer> offLineData = new HashSet<Integer>();
        Set<Integer> onLineData = new HashSet<Integer>();
        FileReader fileReader = new FileReader(fileName);
        Scanner scanner = new Scanner(fileReader);
        while(scanner.hasNext()){
            String line = scanner.nextLine();
            String[] tokens = line.split(",");
//            System.out.println(tokens.length);
            if(tokens.length > 1){
                for(int i = 0; i < tokens.length; i++){
//                    System.out.println(tokens[i]);
                    Integer onLineId = Integer.parseInt(tokens[i].trim());
                    onLineData.add(onLineId);
                }
            }
            else {
                Integer offLineId = Integer.parseInt(line);
                offLineData.add(offLineId);
            }
        }
        Set<Integer> onEoff = diffSets(onLineData, offLineData);
        Set<Integer> offEon = diffSets(offLineData, onLineData);
        System.out.println("onEoff: " + onEoff);
        System.out.println("offEon: " + offEon);
    }

    public static Set<Integer> diffSets(Set<Integer> s1, Set<Integer> s2){
        Set<Integer> remainData = new HashSet<Integer>();
        for(Integer i: s1){
            if(!s2.contains(i)){
                remainData.add(i);
            }
        }
        return remainData;
    }

    public static void tearData(String fileName)throws FileNotFoundException {
        FileReader fileReader = new FileReader(fileName);
        Scanner scanner = new Scanner(fileReader);
        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            tearLine(line);
        }
    }

    private static void tearLine(String line) {
        String[] tokens = line.split("\"");
        if(tokens.length == 1){
            System.out.println(line);
        }
        else if(tokens.length > 1){
            String[] shopIds = tokens[1].split(",");
            for(int i = 0; i < shopIds.length; i++){
                System.out.print(tokens[0].trim()+"\t"+shopIds[i]+"\n");
            }
        }
    }

    private static void calEstStat(String fileName) throws FileNotFoundException {
        FileReader fileReader = new FileReader(fileName);
        Scanner scanner = new Scanner(fileReader);
        int correct = 0;
        int total = 0;

        int tp = 0;
        int tn = 0;
        int fp = 0;
        int fn = 0;

        double calSum= 0.0;
        double estSum= 0.0;
        int count = 0;

        int evaN = 3;

        while(scanner.hasNext() ) {
            count++;
            String line = scanner.nextLine();
            String [] tokens = line.split("[ \t]+");
//            System.out.println(tokens[0]);
//            System.out.println(tokens.length);
//            System.out.println(line);
            if(tokens.length != 13){
                System.out.println("%%%");
            }
            Double cal = Double.parseDouble(tokens[evaN]);
            Double est = Double.parseDouble(tokens[evaN+1]);

            if(cal == 1 && est > THRESHOLD){
                tp++;
            }
            else if(cal == 0 && est > THRESHOLD){
                fp++;
            }
            else if(cal == 0 && est <= THRESHOLD){
                tn++;
            }
            else if(cal == 1 && est <= THRESHOLD){
                fn++;
            }
            calSum+=cal;
            estSum+=est;

        }
        System.out.println("count:" + count);
        System.out.println("calSum:" + calSum);
        System.out.println("estSum:" + estSum);
        System.out.println("tp:" + tp);
        System.out.println("fp:" + fp);
        System.out.println("tn:" + tn);
        System.out.println("fn:" + fn);

        System.out.println("accuracy: " + (double)(tp + tn)/(tp+tn+fp+fn));
        System.out.println("precision: " + (double)tp/(tp+fp));
        System.out.println("recall: " + (double)tp/(tp+fn));
    }

    private static void buildFeatureLabelMap(String fileName) throws FileNotFoundException {
        FileReader fileReader = new FileReader(fileName);
        Scanner scanner = new Scanner(fileReader);

        while(scanner.hasNext() ) {
            String line = scanner.nextLine();
            String[]res = parseFLL(line);
        }
    }

    private static String[] parseFLL(String line) {

//        String sample = "((11,[0,1,2,3,4,5,6,7,8,9,10],[1.0,0.0,1.0,0.0,1.0,0.0,1.0,1.0,0.0,0.0,0.0]),0.0,0.0)";
        String [] tokens = line.split("[,()\\[\\]]+");
        int len = tokens.length;
        System.out.println(tokens[len-2]);

        return tokens;
    }

    private static void evaluateML(String fileName) throws FileNotFoundException {
        FileReader fileReader = new FileReader(fileName);
        Scanner scanner = new Scanner(fileReader);
        int correct = 0;
        int total = 0;

        int tp = 0;
        int tn = 0;
        int fp = 0;
        int fn = 0;

        double calSum= 0.0;
        double estSum= 0.0;
        int count = 0;

        int evaN = 1;

        while(scanner.hasNext() ) {
            count++;
            String line = scanner.nextLine();
            String [] tokens = line.split("[,()]+");
//            System.out.println(tokens[1]);
//            System.out.println(tokens.length);
//            System.out.println(line);

            Double cal = Double.parseDouble(tokens[evaN]);
            Double est = Double.parseDouble(tokens[evaN+1]);

            if(cal > THRESHOLD && est > THRESHOLD){
                tp++;
            }
            else if(cal < THRESHOLD && est > THRESHOLD){
                fp++;
            }
            else if(cal < THRESHOLD && est <= THRESHOLD){
                tn++;
            }
            else if(cal > THRESHOLD && est <= THRESHOLD){
                fn++;
            }
            calSum+=cal;
            estSum+=est;

        }
        System.out.println("count:" + count);
        System.out.println("calSum:" + calSum);
        System.out.println("estSum:" + estSum);
        System.out.println("tp:" + tp);
        System.out.println("fp:" + fp);
        System.out.println("tn:" + tn);
        System.out.println("fn:" + fn);

        System.out.println("accuracy: " + (double)(tp + tn)/(tp+tn+fp+fn));
        System.out.println("precision: " + (double)tp/(tp+fp));
        System.out.println("recall: " + (double)tp/(tp+fn));
    }

    private static void dataCompare(String fileName, int cityLevel, int minusMonth, String cityName) throws FileNotFoundException {
        FileReader fileReader = new FileReader(fileName);
        Scanner scanner = new Scanner(fileReader);

        int count = 0;

        int tpOri = 0;
        int tnOri = 0;
        int fpOri = 0;
        int fnOri = 0;

        int tpML = 0;
        int tnML = 0;
        int fpML = 0;
        int fnML = 0;

        double calSum = 0.0;
        double estSumOri = 0.0;
        double estSumML = 0.0;

        while(scanner.hasNext() ) {
            String line = scanner.nextLine();

            double[]res = evaluateOriAndML(line, cityLevel, minusMonth, cityName);
            Double cal = res[0];
            Double estOri = res[1];
            Double estML = res[2];

            if(res[3] == 1){
                if(cal > THRESHOLD && estOri > THRESHOLD){
                    tpOri++;
                }
                else if(cal < THRESHOLD && estOri > THRESHOLD){
                    fpOri++;
                }
                else if(cal < THRESHOLD && estOri <= THRESHOLD){
                    tnOri++;
                }
                else if(cal > THRESHOLD && estOri <= THRESHOLD){
                    fnOri++;
                }

                if(cal > THRESHOLD && estML > THRESHOLD){
                    tpML++;
                }
                else if(cal < THRESHOLD && estML > THRESHOLD){
                    fpML++;
                }
                else if(cal < THRESHOLD && estML <= THRESHOLD){
                    tnML++;
                }
                else if(cal > THRESHOLD && estML <= THRESHOLD){
                    fnML++;
                }
                count++;
                calSum+=cal;
                estSumOri+=estOri;
                estSumML+=estML;
            }
        }

        System.out.println("count:" + count);
        System.out.println("calSum:" + calSum);
        System.out.println("estSumOri:" + estSumOri);
        System.out.println("estSumML:" + estSumML);


        // ORI
        System.out.println("tpOri:" + tpOri);
        System.out.println("fpOri:" + fpOri);
        System.out.println("tnOri:" + tnOri);
        System.out.println("fnOri:" + fnOri);

        double pOri = (double)tpOri/(tpOri+fpOri);
        double rOri = (double)tpOri/(tpOri+fnOri);
        double fOri = (double)2*pOri*rOri/(pOri+rOri);
        System.out.println("accuracy Ori: " + (double)(tpOri + tnOri)/(tpOri+tnOri+fpOri+fnOri));
        System.out.println("precision Ori: " + pOri);
        System.out.println("recall Ori: " + rOri);
        System.out.println("F1 Ori: " + fOri);

        // ML
        System.out.println("tpML:" + tpML);
        System.out.println("fpML:" + fpML);
        System.out.println("tnML:" + tnML);
        System.out.println("fnML:" + fnML);

        double pML = (double)tpML/(tpML+fpML);
        double rML = (double)tpML/(tpML+fnML);
        double fML = (double)2*pML*rML/(pML+rML);
        System.out.println("accuracy ML: " + (double)(tpML + tnML)/(tpML+tnML+fpML+fnML));
        System.out.println("precision ML: " + pML);
        System.out.println("recall ML: " + rML);
        System.out.println("F1 ML: " + fML);
    }

    private static double[] evaluateOriAndML(String line, int cityLevel, int minusMonth, String cityName) {
        double[] res = new double[4];
        String[] tokens = line.split("[\t]+");
        int lastIndex = tokens.length-1;

        if(cityLevel == 0){
            res[0] = Double.parseDouble(tokens[minusMonth]);
            res[1] = Double.parseDouble(tokens[minusMonth+1]);
            res[2] = Double.parseDouble(tokens[minusMonth+2]);
            if(cityName.length() == 0){
                res[3] = 1;
            }
            else {
                if(cityName.equals(tokens[lastIndex])){
                    res[3] = 1;
                }
            }
        }
        else {
            Integer accountCityLevel = Integer.parseInt(tokens[9+cityLevel]);
            if(accountCityLevel == 1){
                res[0] = Double.parseDouble(tokens[minusMonth]);
                res[1] = Double.parseDouble(tokens[minusMonth+1]);
                res[2] = Double.parseDouble(tokens[minusMonth+2]);
                res[3] = 1;
            }
        }




//        System.out.println(tokens.length);
        return res;
    }

}


