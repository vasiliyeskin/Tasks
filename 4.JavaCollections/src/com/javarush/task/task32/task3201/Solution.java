package com.javarush.task.task32.task3201;

import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) throws Exception {
        String filePath = args[0];
        String position = args[1];
        String text = args[2];

        RandomAccessFile raf = new RandomAccessFile(filePath, "rw");

        long lposition = Long.parseLong(position);

        if(lposition < raf.length())
            raf.seek(lposition);
        else
            raf.seek(raf.length());

        raf.write(text.getBytes());

        raf.close();
    }
}
