package com.javarush.task.task07.task0718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Проверка на упорядоченность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        ArrayList<String> ss = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sss = "";
        for (int i = 0; i < 10; i++) {
            sss = reader.readLine();
            ss.add(sss);
        }

        int length = 0;

        for (int i = 0; i < 10; i++) {
            if(length > ss.get(i).length()) {
                System.out.println((i));
                return;
            }
            else
                length = ss.get(i).length();
        }
    }
}

