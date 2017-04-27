package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/
public class Solution {

    public void recursion(int n) {
        int a = 2;
        while (a <= n) {
            if ((n % a) == 0) {
                if (a != n) {
                    System.out.print(a + " ");
                    recursion(n / a);
                } else {
                    System.out.print(a);
                }
                return;
            }
            a++;
        }
    }

  /*  public void recursion(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if(n%i == 0)
            {
                System.out.print(i + " ");
                recursion(n/i);
                return;
            }
        }

        System.out.print(n);
    }*/
}
