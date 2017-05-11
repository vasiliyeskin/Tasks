package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vaisiliy Es'kin on 05/11/17.
 */
public class Solution {
    public static void main(String[] args) {
        HashMapStorageStrategy strategy = new HashMapStorageStrategy();
        testStrategy(strategy, 100L);

        OurHashMapStorageStrategy ourHashMapStorageStrategy = new OurHashMapStorageStrategy();
        testStrategy(ourHashMapStorageStrategy, 100L);

        FileStorageStrategy fileStorageStrategy = new FileStorageStrategy();
        testStrategy(fileStorageStrategy, 100L);

        OurHashBiMapStorageStrategy ourHashBiMapStorageStrategy = new OurHashBiMapStorageStrategy();
        testStrategy(ourHashBiMapStorageStrategy, 100L);

        HashBiMapStorageStrategy hbi = new HashBiMapStorageStrategy();
        testStrategy(hbi, 100L);

        DualHashBidiMapStorageStrategy dhb = new DualHashBidiMapStorageStrategy();
        testStrategy(dhb, 100L);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings)
    {
        Set<Long> longs = new HashSet<>();
        for(String s: strings)
        {
            longs.add(shortener.getId(s));
        }
        return longs;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys)
    {
        Set<String> strings = new HashSet<>();
        for(Long s: keys)
        {
            strings.add(shortener.getString(s));
        }
        return strings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber)
    {
        System.out.println(strategy.getClass().getSimpleName());

        Set<String> strings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            strings.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        Long time1;
        time1 = new Date().getTime();
        Set<Long> longs = getIds(shortener, strings);
        Helper.printMessage(String.valueOf(new Date().getTime() - time1));

        time1 = new Date().getTime();
        Set<String> stringSet = getStrings(shortener, longs);
        Helper.printMessage(String.valueOf(new Date().getTime() - time1));

        if (stringSet.size() == strings.size()) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }

    }
}
