package com.javarush.task.task32.task3213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor Dpljr");
        System.out.println(decode(reader, -3));  //Hello Amigo

    }

    public static String decode(StringReader reader, int key) throws IOException {
        if(reader == null)
            return "";

        StringWriter sw = new StringWriter();
        BufferedReader reader1 = new BufferedReader(reader);
        int result = 0;
        while(reader1.ready() && (result = reader1.read())!=-1)
        {
            sw.write(result + key);
        }

        return sw.toString();
    }

}
