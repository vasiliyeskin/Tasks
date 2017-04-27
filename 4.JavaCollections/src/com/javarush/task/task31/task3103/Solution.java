package com.javarush.task.task31.task3103;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/* 
Своя реализация
*/
public class Solution {
    public static byte[] readBytes(String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(fileName));
    }

    public static List<String> readLines(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName));
    }

    public static void writeBytes(String fileName, byte[] bytes) throws IOException {
        Files.write(Paths.get(fileName), bytes);
    }

    public static void copy(String resourceFileName, String destinationFileName) throws IOException {
        Files.copy(Paths.get(resourceFileName),Paths.get(destinationFileName));
    }

    public static void main(String[] args) throws IOException {
        String fileName = "E:\\test\\t1.txt";
        String destinationFileName = "E:\\test\\tagg.txt";

        copy(fileName, destinationFileName);
        System.out.println(new String(readBytes(fileName)));
        System.out.println(readLines(fileName));

        writeBytes("E:\\test\\taa.txt", readBytes(fileName));
    }
}
