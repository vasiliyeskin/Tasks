package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object i = Integer.valueOf(42);
        String s = (String)i;
    }

    public void methodThrowsNullPointerException() {
        String s = null;
        s.equals("fd");
    }

    public static void main(String[] args) {
        VeryComplexClass v = new VeryComplexClass();
        v.methodThrowsClassCastException();
        v.methodThrowsNullPointerException();
    }
}
