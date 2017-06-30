package com.javarush.task.task08.task0818;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static HashMap<String, Integer> createMap() {
        //напишите тут ваш код
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 3232);
        map.put("ab", 3232);
        map.put("as", 322);
        map.put("ac", 3232);
        map.put("ad", 3232);
        map.put("ae", 32);
        map.put("aw", 3232);
        map.put("aq",32);
        map.put("au", 322);
        map.put("ai", 32);

        return map;
    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        //напишите тут ваш код
        HashMap<String, Integer> copy = new HashMap<>(map);

        Iterator<Map.Entry<String, Integer>> iterator = copy.entrySet().iterator();

        while (iterator.hasNext())
        {
            //получение «пары» элементов
            Map.Entry<String, Integer> pair = iterator.next();
            if(pair.getValue() < 500)
                map.remove(pair.getKey());
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map1 = createMap();
        removeItemFromMap(map1);

    }
}