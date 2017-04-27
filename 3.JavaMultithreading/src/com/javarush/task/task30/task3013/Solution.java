package com.javarush.task.task30.task3013;

/* 
video
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int number = Integer.MAX_VALUE - 133;
        System.out.println(Integer.toString(number, 2));

        String result = Integer.toString(solution.resetLowerBits(number), 2);
        System.out.println(result);

        number = 258;
        System.out.println(Integer.toString(number, 2));

        result = Integer.toString(solution.resetLowerBits(number), 2);
        System.out.println(result);

        number = Integer.MAX_VALUE;
        System.out.println(Integer.toString(number, 2));

        result = Integer.toString(solution.resetLowerBits(number), 2);
        System.out.println(result);

         number = 258;
        System.out.println(number);
        System.out.println(solution.resetLowerBits(number));

       number = 3456;
        System.out.println(number);
        System.out.println(solution.resetLowerBits(number));

        number = 1;
        System.out.println(number);
        System.out.println(solution.resetLowerBits(number));

        number = 4;
        System.out.println(number);
        System.out.println(solution.resetLowerBits(number));

        number = Integer.MAX_VALUE - 133;
        System.out.println(number);
        System.out.println(solution.resetLowerBits(number));

        number = Integer.MAX_VALUE;
        System.out.println(number);
        System.out.println(solution.resetLowerBits(number));
    }

    public int resetLowerBits(int number) {
        //напишите тут ваш код
       // return (number|(~number&(number>>1)))&(~((number|(~number&(number>>1)))>>1));
        number |= number >> 1;
        number |= number >> 2;
        number |= number >> 4;
        number |= number >> 8;
        number |= number >> 16;
        return number&(~(number>>1));
    }
}