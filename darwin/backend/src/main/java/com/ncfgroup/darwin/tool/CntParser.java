package com.ncfgroup.darwin.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class CntParser {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		File output = new File(args[0]);
		BufferedReader br = new BufferedReader(new FileReader(new File(args[1])));
		
//		File output = new File("testOut1.txt");
//		BufferedReader br = new BufferedReader(new FileReader(new File("testIn.txt")));
		
		
		
    	String dataLine;
    	
    	Map<String, Integer> cntMap = new HashMap<String, Integer>();
    	Map<String, ArrayList<String>> idsMap = new HashMap<String, ArrayList<String>>();
    	
    	while((dataLine = br.readLine()) != null) 
    	{
    		
//    		System.out.println(dataLine);
    		String[] tokens = dataLine.split(",");
    		if(tokens.length > 1)
    		{
    			String outLine = "";
    			for(int i = 0; i < tokens.length; i++)
    			{
    				int index = tokens[i].indexOf(':');
    				String dataPart = tokens[i].substring(index + 1);
    				outLine += dataPart + ",";

    			}
//    			System.out.println(outLine);
    			FileUtils.writeStringToFile(output, outLine + "\n", true);
    		}
			
    	}
//    	System.out.println("KEYS: ***********************");
//    	for(Map.Entry<String, Integer> entry : cntMap.entrySet())
//    	{
//    		System.out.println(entry.getKey());
////    		System.out.println(entry.getValue());
//    	}
//    	System.out.println("VALUES: ***********************");
//    	for(Map.Entry<String, Integer> entry : cntMap.entrySet())
//    	{
////    		System.out.print(entry.getKey() + " : ");
//    		System.out.println(entry.getValue());
//    	}
    	br.close();

	}
		

}
