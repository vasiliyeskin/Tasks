package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/

import java.io.FileInputStream;

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        //напишите тут ваш код
        new FileInputStream("dfs");
    }

    public static void main(String[] args) {
        VeryComplexClass vcs = new VeryComplexClass();
        try {
            vcs.veryComplexMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
