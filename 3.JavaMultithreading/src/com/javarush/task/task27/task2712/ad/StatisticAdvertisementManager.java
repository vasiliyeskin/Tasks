package com.javarush.task.task27.task2712.ad;

import java.util.*;

public class StatisticAdvertisementManager {
    private static AdvertisementStorage storage = AdvertisementStorage.getInstance();

    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();

    public static StatisticAdvertisementManager getInstance() {
        return ourInstance;
    }

    private StatisticAdvertisementManager() {
    }

    public Map<String, Integer> getAllAdvertisement() {
        Map<String, Integer> result = new TreeMap<>(new Comparator() {
            @Override
            public int compare(Object s0, Object s1) {
                if (s0.toString().toLowerCase().compareTo(s1.toString().toLowerCase()) != 0)
                    return s0.toString().toLowerCase().compareTo(s1.toString().toLowerCase());
                else return s0.toString().compareTo(s1.toString());
            }
        });
        for (Advertisement video : storage.list()) {
            result.put(video.getName(), video.getHits());
        }
        return result;
    }
}
