package com.javarush.task.task35.task3509;

import java.util.*;


/* 
Collections & Generics
*/
public class Solution {

    public static void main(String[] args) {
    }

    public static <T> ArrayList<T> newArrayList(T... elements) {
        //напишите тут ваш код
        ArrayList<T> array = new ArrayList<>();
        for(T tt:elements)
        {
            array.add(tt);
        }
        return array;
    }

    public static <T> HashSet<T> newHashSet(T... elements) {
        //напишите тут ваш код
        HashSet<T> array = new HashSet<>();
        for(T tt:elements)
        {
            array.add(tt);
        }
        return array;
    }

    public static <K,V> HashMap<K, V> newHashMap(List<? extends K> keys, List<? extends V> values) {
        //напишите тут ваш код
        HashMap kvHashMap = new HashMap<List<? extends K>, List<? extends V>>();


        if(keys.size() == values.size())
        {
            for (int i = 0; i < keys.size(); i++) {
                kvHashMap.put(keys.get(i), values.get(i));
            }
        }
        else
        {
            throw new IllegalArgumentException();
        }

        return kvHashMap;
    }
}
