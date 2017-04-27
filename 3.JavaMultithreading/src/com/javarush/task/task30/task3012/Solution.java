package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        for (int i = 1; i < 300; i++) {
            solution.createExpression(i);
        }
    }

    private String s = "";

    public void createExpression(int number) {
        //напишите тут ваш код
        int[] mat = {1, 3, 9, 27, 81, 243, 729, 2187};

        boolean sign = number < 0;
        number = Math.abs(number);

        int min = 3000;
        int imin = 0;
        int sum = 0;
        for (int i = 0; i < mat.length; i++) {
            if (Math.abs(number) > mat[i] / 2) {
                imin = i;
                sum += mat[i];
            }
        }

        if (number == 0) return;

        if (sign) {
            s = (" - " + mat[imin]) + s + " ";
            createExpression(-(number - mat[imin]));
        } else {
            s = (" + " + mat[imin]) + s + " ";
            createExpression(number - mat[imin]);
        }

        if (Thread.currentThread().getStackTrace()[2].getMethodName() != "createExpression") {
            System.out.println(number + " = " + s);
            s = "";
        }
    }
}