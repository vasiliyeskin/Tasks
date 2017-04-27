package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws IOException {
        String filePath = args[0];
        int position = Integer.parseInt(args[1]);
        String text = args[2];

        RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
        raf.seek(position);

        byte[] bytes = new byte[text.length()];
        boolean bln = position + bytes.length < raf.length();
        raf.read(bytes, 0, bytes.length);

        if(bln) {
            raf.seek(raf.length());
            if(text.equals(convertByteToString(bytes)))
                raf.write("true".getBytes());
            else
                raf.write("false".getBytes());
        }
        else {
            raf.seek(raf.length());
            raf.write("false".getBytes());
        }

        raf.close();
    }

    public static String convertByteToString(byte readBytes[])
    {
        return new String(readBytes);
    }
}
