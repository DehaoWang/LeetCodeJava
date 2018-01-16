package com.leetcodejava.dataAnalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by wangdehao on 18/1/9.
 */
public class shopVisitAnalysis {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("SVA");
        int dayLenForComparison = 7;

        Map<Integer, Map<Integer, VisitModel>> lost2ShopProfile = new HashMap<Integer, Map<Integer, VisitModel>>();


//        File file = new File("/Users/wangdehao/ADP_Projects/data/labelMining/toBeAnalyse6-budget");
        File file = new File("/Users/wangdehao/ADP_Projects/data/labelMining/toBeAnalyse5-timeslot");
        FileReader fileReader = new FileReader(file);
        Scanner scanner = new Scanner(fileReader);
        int cnt = 0;
        while(scanner.hasNextLine()){
            cnt++;
            String line = scanner.nextLine();
            String[] tokens = line.split("\t");
//            System.out.println(tokens.length);
            Integer shopId = Integer.parseInt(tokens[0]);
            Integer dateDiff = Integer.parseInt(tokens[1]);
            Integer lost = Integer.parseInt(tokens[2]);
            Integer visit = Integer.parseInt(tokens[3]);

            Map<Integer, VisitModel> modelMap = lost2ShopProfile.get(lost);
            if(modelMap == null){
                modelMap = new HashMap<Integer, VisitModel>();
                lost2ShopProfile.put(lost, modelMap);
            }
            VisitModel visitModel = modelMap.get(shopId);
            if(visitModel == null){
                visitModel = new VisitModel();
                modelMap.put(shopId, visitModel);
            }
            if(dateDiff > 0 && dateDiff < dayLenForComparison){
                visitModel.increaseSumNear(visit);
                visitModel.increaseCntNear();
            }else if(dateDiff >= dayLenForComparison && dateDiff < 2 * dayLenForComparison){
                visitModel.increaseSumFar(visit);
                visitModel.increaseCntFar();
            }
        }

        System.out.println("scan finish, total cnt = "+cnt);
        System.out.println("check map: 0-size="+lost2ShopProfile.get(0).size());
        System.out.println("check map: 1-size="+lost2ShopProfile.get(1).size());

        int lostCnt = 0;
        int nonLostCnt = 0;
        int lostDescVisitCnt = 0;
        int nonLostDescVisitCnt = 0;

        for(Integer lost: lost2ShopProfile.keySet()){
            Map<Integer, VisitModel> modelMap = lost2ShopProfile.get(lost);
            for(Integer shopId: modelMap.keySet()){
                VisitModel visitModel = modelMap.get(shopId);
                visitModel.calculateModel();
                if(lost == 1){
                    lostCnt++;
                    if(visitModel.getFar2NearDecrementVisitAvg() == 1){
                        lostDescVisitCnt++;
                    }
                }
                if(lost == 0){
                    nonLostCnt++;
                    if(visitModel.getFar2NearDecrementVisitAvg() == 1){
                        nonLostDescVisitCnt++;
                    }
                }
//                System.out.println("shopId="+shopId+",visitModel="+visitModel.toString());
            }
        }
//        VisitModel visitModel = lost2ShopProfile.get(1).get(503157);
//        System.out.println(visitModel.toString());

        System.out.println("lostCnt="+lostCnt+",lostDescVisitCnt="+lostDescVisitCnt+",rate="+(double)lostDescVisitCnt/lostCnt);
        System.out.println("nonLostCnt="+nonLostCnt+",nonLostDescVisitCnt="+nonLostDescVisitCnt+",rate="+(double)nonLostDescVisitCnt/nonLostCnt);
    }

//    public static
}
