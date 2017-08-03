package com.leetcodejava.algorithms.lc476;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int ans = Integer.parseInt("1101",2);
        int and = 15 & 3;
        System.out.println("test: "+ans);
        System.out.println("test: "+and);

        int num1 = 2;

        int[] bin1 = com.leetcodejava.algorithms.lc461.Main.convertNum2Binary(num1);
        com.leetcodejava.algorithms.lc461.Main.printBinary(bin1);
        System.out.println(convertBinary2Num(bin1));
        int[] bin2 = getComplementOfBinary(bin1);
        com.leetcodejava.algorithms.lc461.Main.printBinary(bin2);
        System.out.println(convertBinary2Num(bin2));

        System.out.println(getComplementNum(num1));
	}

    public static int[] getComplementOfBinary(int[] num){
        boolean flipFlag = false;
        for(int i = 0; i < num.length; i++){
            if(num[i] == 0 && !flipFlag){
                continue;
            }
            else {
                flipFlag = true;
                num[i] = 1 - num[i];
            }
        }
        return num;
    }

    public static int convertBinary2Num(int[] bin){
        int num = 0;
        for(int i = 0; i < bin.length; i++){
            num += bin[i] * (Math.pow(2, bin.length-1-i));
        }
        return num;
    }

    public static int getComplementNum(int num){
        //System.out.println("binary bits of num:"+Integer.toBinaryString(num));
        //System.out.println("binary bits of HigestOneBit of num:"+Integer.toBinaryString(Integer.highestOneBit(num)));
        int mask = (Integer.highestOneBit(num) << 1) -1;

        System.out.println(mask);
        System.out.println("binary bits of mask:"+Integer.toBinaryString(mask));
        num = ~num;
//        System.out.println(num);
//        System.out.println(~num);
        return num & mask;
    }


}
