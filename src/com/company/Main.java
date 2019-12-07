package com.company;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String input="";
        while(true){
            System.out.println("sin ？");
            input=new Scanner(System.in).nextLine();
            if(input.equals("exit"))break;
            String param=input;
            System.out.println("numDigit ?");
            input=new Scanner(System.in).nextLine();
            int numDigit=Integer.parseInt(input);
            System.out.println(new ExtremeSin(param,numDigit,10000).execute().toString());
        }
    }

}
