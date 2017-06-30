package com.javarush.task.task06.task0606;

import java.io.*;

/* 
Чётные и нечётные циферки
*/

public class Solution {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        even = 0;
        odd = 0;
        String s = bufferedReader.readLine();
        char[] chs = s.toCharArray();
        for(int i = 0; i < chs.length; i++)
        {
            if(Integer.parseInt(chs[i] + "") % 2 == 0)even++;
            else odd++;
        }

        System.out.println(String.format("Even: %1$d Odd: %2$d",Solution.even , Solution.odd));
    }
}
