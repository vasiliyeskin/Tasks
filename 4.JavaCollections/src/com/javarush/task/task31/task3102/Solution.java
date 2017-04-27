package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> fileList = new ArrayList<>();
        Stack<File> stack = new Stack<>();
        File rootDir = new File(root);
        stack.push(rootDir);
        while (!stack.isEmpty()) {
            File child = stack.pop();
            if (child.isDirectory()) {
                for (File f : child.listFiles()) {
                    stack.push(f);
                }
            } else if (child.isFile()) {
                fileList.add(child.getAbsolutePath());
            }
        }
        return fileList;
    }

    public static void main(String[] args) throws IOException {
        List<String> fileList = getFileTree("e:\\JAVA\\JavaRushHomeWork_darkside\\JavaRushTasks\\4.JavaCollections\\");
        for (String s : fileList) {
            System.out.println(s);
        }
    }
}
