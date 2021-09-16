package com.试验田.文档查重;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Demo {
    static ArrayList<Data> d = new ArrayList<Data>();
    public static void main(String[] args) {
        //输入对照文本地址
        Scanner sc = new Scanner(System.in);
//        String s1 = args[0];
//        String s2 = args[1];
//        String s3 = args[2];
//C:\Users\高维投影\Desktop\查重文件\orig.txt
//C:\Users\高维投影\Desktop\查重文件\orig_0.8_add.txt
//
        String s1 ="D:\\查重文件\\orig.txt";
        String s2 ="D:\\查重文件\\orig_0.8_del.txt";

        File file1 = new File(s1);
        File file2 = new File(s2);


        ArrayList<String> array1 = new ArrayList<String>();
        ArrayList<String> array2 = new ArrayList<String>();

        //语句合并方法调用
        reDivision(division(file1),array1);
        reDivision(division(file2),array2);

        //输出比率
        double rate = calculation(array1,array2);
        String s = "重复率";
        s = s +String.format("%.2f",rate*100) + "%";

        System.out.println(rate);
    }
    //文本初步分割
    public static String[] division(File file) {
        String ss[] = null;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            int length = inputStream.available();
            byte bytes[] = new byte[length];
            inputStream.read(bytes);

            String s = new String(bytes,"UTF-8");

            if(s.isEmpty()){
                System.out.println("文件为空");
                System.exit(0);
            }
            //用空格取代常用的标点符号
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '。' || c == '！' || c == '？' || c == '：' || c == '；' || c == '“' || c == '”' || c == '\n') {
                    sb.append(' ');
                } else if(c!='，') {
                    sb.append(c);
                }
            }
            String s1 = sb.toString();
            ss = s1.split(" {1,}");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ss;
    }
    //语句合并
    public static void reDivision(String ss[], ArrayList<String>array){
        int j =0;

        while(j<ss.length){
            String s = new String("");
             while(j < ss.length){
                 s =s + ss[j] ;
                 j++;
                 if(s.length()>=13){
                     array.add(s);
                     break;
                 }
             }
        }
    }
    //计算方法
    public static double calculation(ArrayList<String>array1,ArrayList<String>array2){
        for(String s1 : array2){
            Data dx = new Data();
            d.add(dx);
            for(String s2 : array1){
                int sameQuantity = 0;
                for(int i=0; i<s1.length(); i++){
                    for(int j=0; j<s2.length(); j++){
                        if(s1.charAt(i)==s2.charAt(j)){
                            sameQuantity++;
                            break;
                        }
                    }
                }
                if(dx.getSameQuantity()<sameQuantity){
                    dx.setTotal(s1.length() + s2.length() - sameQuantity) ;
                    dx.setSameQuantity(sameQuantity);
                    dx.setRate(dx.getSameQuantity()/dx.getTotal());
                }
            }

        }
        int total = 0;
        for(int i=0; i<array2.size(); i++){
            total = total + array2.get(i).length();
        }
        double rate =0;
        for(int i =0; i < d.size(); i++){
            rate = rate + d.get(i).getRate() * d.get(i).getTotal()/total;
        }
        return  rate;

    }
}
