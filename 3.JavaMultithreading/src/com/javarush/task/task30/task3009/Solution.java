package com.javarush.task.task30.task3009;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }

    private static Set<Integer> getRadix(String number)
    {
        int num;
        Set<Integer> integerSet = new HashSet<>();
        try {
            num = Integer.parseInt(number,10);
        } catch (NumberFormatException e) {
            return integerSet;
        }

        String snumber = "";
        for (int i = 2; i <= 36 ; i++) {
            snumber = Integer.toUnsignedString(num,i);
            if(snumber.equals(new StringBuilder(snumber).reverse().toString()))
                integerSet.add(i);
        }

        return integerSet;
    }
}