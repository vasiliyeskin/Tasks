package com.javarush.task.task39.task3908;

import java.util.HashMap;
import java.util.Map;

/*
Возможен ли палиндром?
*/
public class Solution {
    public static void main(String[] args) {
        String ss = "asdasdasdasd";
        System.out.println(ss);
        System.out.println(isPalindromePermutation(ss));
        ss = "aaaadddd";
        System.out.println(ss);
        System.out.println(isPalindromePermutation(ss));
        ss = "aaaddd";
        System.out.println(ss);
        System.out.println(isPalindromePermutation(ss));
    }

    public static boolean isPalindromePermutation(String s) {
        Map<Character, Integer> map = new HashMap<>();

        char[] chs = s.toLowerCase().toCharArray();
        for (char c : chs) {
            if (!map.containsKey(c))
                map.put(c, 1);
            else
                map.put(c, map.get(c) + 1);
        }

        boolean b = true;

        if (chs.length % 2 == 0) {
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                if (entry.getValue() % 2 == 1) {
                    b = false;
                    break;
                }
            }
        } else {
            boolean firstRepeate = true;
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                if (entry.getValue() % 2 == 1) {
                    if (firstRepeate)
                        firstRepeate = false;
                    else {
                        b = false;
                        break;
                    }
                }
            }
        }

        return b;
    }
}
