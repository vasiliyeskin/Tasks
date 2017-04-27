package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?

E:\GAMES
*/
public class Solution {

    static int countFolder = 0;
    static int countFiles = 0;
    static long countSize = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sdir = reader.readLine();
        Path p = Paths.get(sdir);
        if (!Files.isDirectory(p)) {
            System.out.println(p.toAbsolutePath().toString() + " - не папка");
            return;
        }

        System.out.println(sdir);

        try {
            Files.walkFileTree(p, new MyFileVisitor());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Всего папок - " + (countFolder - 1));
        System.out.println("Всего файлов - " + countFiles);
        System.out.println("Общий размер - " + countSize);
    }

    static class MyFileVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            countFiles++;
            countSize += attrs.size();
            return super.visitFile(file, attrs);
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            countFolder++;
            return super.preVisitDirectory(dir, attrs);
        }
    }

}
