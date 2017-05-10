package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        //напишите тут ваш код
        int size = 0;
        for (Map.Entry<K,List<V>> e: map.entrySet())
        {
            size += e.getValue().size();
        }
        return size;
    }

    @Override
    public V put(K key, V value) {
        //напишите тут ваш код
        List<V> v;
        if(map.containsKey(key))
        {
            v = map.get(key);
            V end = v.get(v.size()-1);
            if(v.size() < repeatCount)
            {
                v.add(value);
            }
            else
            {
                v.remove(0);
                v.add(value);
            }
            map.put(key, v);
            return end;
        }
        else
        {
            v = new ArrayList<>();
            v.add(value);
            map.put(key, v);
            return null;
        }
    }

    @Override
    public V remove(Object key) {
        //напишите тут ваш код
        if(map.containsKey(key))
        {
            List<V> list = map.get(key);
            if(list.size() ==0 )
            {
                map.remove(key);
                return null;
            }
            V start = list.get(0);
            list.remove(0);
            if(list.size() == 0)
                map.remove(key);
            return start;
        }
        return null;
    }

    @Override
    public Set<K> keySet() {
        //напишите тут ваш код
        Set<K> set = new HashSet<>();
        for (Map.Entry<K,List<V>> e: map.entrySet())
        {
            set.add(e.getKey());
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        //напишите тут ваш код
        Collection<V> vCollection = new ArrayList<>();
        for (Map.Entry<K,List<V>> e: map.entrySet())
        {
            vCollection.addAll(e.getValue());
        }
        return vCollection;
    }

    @Override
    public boolean containsKey(Object key) {
        //напишите тут ваш код
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        //напишите тут ваш код
        for (Map.Entry<K,List<V>> e: map.entrySet())
        {
            if(e.getValue().contains(value))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}