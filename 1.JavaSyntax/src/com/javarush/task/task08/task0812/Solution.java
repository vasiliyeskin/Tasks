package com.javarush.task.task08.task0812;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* 
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        ArrayList<Integer> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            list.add(Integer.parseInt(reader.readLine()));
        }

       // list.sort((Integer i1, Integer i2) -> i1.compareTo(i2));

        int max = 0;
        int buffer = 1;
        int i = (int) list.get(0);
        for (int j = 1; j < 10; j++) {
            if (i == (int) list.get(j)) {
                buffer++;
            } else {
                i = (int) list.get(j);
                buffer = 1;
            }
            if (max < buffer)
                max = buffer;
        }

        System.out.println(max);

    }
}