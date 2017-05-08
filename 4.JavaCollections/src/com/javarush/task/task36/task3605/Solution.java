package com.javarush.task.task36.task3605;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        TreeSet<String> treeSet = new TreeSet<>();

        FileReader fileReader = new FileReader(args[0]);
        int buffer = fileReader.read();
        String s = "";
        while(buffer != -1)
        {
            s = String.valueOf((char)buffer);
            if(s.matches("\\w") && s.matches("[^0-9]") && !s.matches("\\p{Cntrl}"))
                treeSet.add(s.toLowerCase());
            buffer = fileReader.read();
        }

        //выводим
        Iterator iterator = treeSet.iterator();
        if (treeSet.size() >= 5) {

            int count = 0;
            while (iterator.hasNext() && count < 5) {
                System.out.print(iterator.next());
                count ++;
            }

        } else {
            while (iterator.hasNext()) {
                System.out.print(iterator.next());
            }
        }

    }
}
