package com.javarush.task.task05.task0507;

/* 
Среднее арифметическое
*/

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        Scanner scanner = new Scanner(System.in);

        int num = 0;
        int count = -1, sum = 0;
        do
        {
            count++;
            sum += num;
            num = scanner.nextInt();
        }while (num != -1);

        System.out.println(((double)sum / count));
    }
}

