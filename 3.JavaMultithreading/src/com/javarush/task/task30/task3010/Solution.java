package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        try {
            String number = args[0];
            boolean correct = false;

            for (int i = 2; i <= 36 ; i++) {
                try {
                    new BigInteger(number, i);
                    System.out.println(i);
                    correct = true;
                    break;
                } catch (Exception e) {
                }
            }

            if(!correct) System.out.println("incorrect");
        } catch (Exception e) {
        }
    }
}