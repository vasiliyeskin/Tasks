package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        ArrayList<String> ss = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int max = 0, min = 0;
        String sss = "";
        for (int i = 0; i < 10; i++) {
            sss = reader.readLine();
            if(sss.length() > max)
                max = sss.length();
            if(sss.length() < min || min == 0)
                min = sss.length();
            ss.add(sss);
        }

        for (int i = 0; i < 10; i++) {
            if(ss.get(i).length() == max || ss.get(i).length() == min)
            {System.out.println(ss.get(i));
            return;}
        }

    }
}
