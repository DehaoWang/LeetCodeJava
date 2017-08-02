package com.leetcodejava.dataAnalysis;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by wangdehao on 16/12/22.
 */
public class DaMain
{
    public static void main( String[] args ) throws FileNotFoundException {
        System.out.println( "Hello World!" );
        readAndAnalyse("/Users/wangdehao/ADP_Projects/data/shanghaiAnalysis/test5000");
    }

    public static void readAndAnalyse(String fileName) throws FileNotFoundException {
        FileReader fileReader = new FileReader(fileName);
        Scanner scanner = new Scanner(fileReader);
        int count = 0;
        while(scanner.hasNext()){
            int maxLength = 0;
            int currLength = 0;
            int emptyCount = 0;
            String line = scanner.nextLine();
            String[] tokens = line.split("\t");
            for(int i = 0; i < tokens.length; i++){
                if(tokens[i].length() > 0){
//                    System.out.println(i+"-**-"+tokens[i]);
                    currLength++;
                }
                else {
                    if(currLength > maxLength){
                        maxLength = currLength;
                    }
                    currLength = 0;
                }
//                System.out.println(currLength+"/"+maxLength);
            }
            if(currLength > maxLength){
                maxLength = currLength;
            }
            System.out.println(maxLength);

            count++;
        }
//        System.out.println(count);
    }

}
