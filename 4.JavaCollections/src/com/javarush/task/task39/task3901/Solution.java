package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please input your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if(s == null || s.length() == 0)
            return 0;

        int res = 0;
        for(int i=0; i<s.length();i++){
            Set<Character> set = new HashSet<>();
            for(int j = i; j<s.length(); j++){
                if(set.contains(s.charAt(j)))
                    break;
                else
                    set.add(s.charAt(j));
            }
            if(set.size()>res)
                res = set.size();
        }

        return res;
    }
}
