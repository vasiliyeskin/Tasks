package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/
public class Solution {
    public static void main(String[] args) {
        for (int i = 1; i < 1000; i++) {
            System.out.println(i + "   " + isPowerOfThree(i));
        }

    }

    public static boolean isPowerOfThree(int n) {
        if(n == 0)return false;

        while(n > 1)
        {
            if (n % 3 != 0 ) {
                return false;
            }
            n = n / 3;
        }

        if(n==1)
            return true;

        return false;
    }
}
