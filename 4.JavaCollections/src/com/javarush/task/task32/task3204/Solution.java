package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());

        password = getPassword();
        System.out.println(password.toString());
        password = getPassword();
        System.out.println(password.toString());
        password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword()
    {
        ByteArrayOutputStream baos;
        int symbol;
        while (true) {
            baos = new ByteArrayOutputStream();
            boolean[] charType = {false, false, false};
            for (int i = 0; i < 8; i++) {
                symbol = (int) (Math.random() * 3);
                if (symbol == 0) {
                    symbol = (int) (Math.random() * (123 - 97)) + 97;
                    charType[0] = true;
                } else if (symbol == 1) {
                    symbol = (int) (Math.random() * (91 - 65)) + 65;
                    charType[1] = true;
                } else {
                    symbol = (int) (Math.random() * (58 - 48)) + 48;
                    charType[2] = true;
                }

                baos.write(symbol);
            }

            if (charType[0] && charType[1] && charType[2])
                return baos;
        }
    }
}