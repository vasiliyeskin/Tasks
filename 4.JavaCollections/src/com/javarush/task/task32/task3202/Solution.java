package com.javarush.task.task32.task3202;

import java.io.*;

/* 
Читаем из потока
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("testFile.log"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        StringWriter sw = new StringWriter();
        if(is == null)
            sw.write(0);
        else
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            char[] cbuf = new char[512];
            while (reader.ready()) {
                int len = reader.read(cbuf);
                sw.write(cbuf, 0, len);
            }
        }


        return sw;
    }
}