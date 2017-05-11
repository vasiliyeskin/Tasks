package com.javarush.task.task33.task3310.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vaisiliy Es'kin on 05/11/17.
 */
public class HashMapStorageStrategy implements StorageStrategy {
    HashMap<Long, String> data = new HashMap<>();

    @Override
    public boolean containsKey(Long key) {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return data.containsValue(value);
    }

    @Override
    public void put(Long key, String value) {
data.put(key, value);
    }

    @Override
    public Long getKey(String value) {
        for(Map.Entry<Long, String> ms: data.entrySet()) {
            if(value.equals(ms.getValue()))
                return ms.getKey();
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        return data.get(key);
    }
}
