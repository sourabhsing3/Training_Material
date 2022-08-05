package com.examples.java.core;

public class StringToInt {
    public static void main(String[] args) {
        String s =new String("5000");
        Integer num= new Integer(0);
        int p=1;
        for(int i=s.length()-1;i>=0;i--){
            num += (s.charAt(i)-'0')*p;
            p *=10;
        }

        System.out.println(num instanceof Integer);
        System.out.println(num);
    }
}
