package com.javarush.task.task33.task3310.strategy;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Vaisiliy Es'kin on 05/11/17.
 */
public class Entry implements Serializable {
    Long key;
    String value;
    Entry next;
    int hash;

    public Entry( int hash, Long key, String value, Entry next) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.hash = hash;
    }

    public Long getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return key +
                "=" + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry)) return false;

        Entry entry = (Entry) o;

        if (getKey() != null ? !getKey().equals(entry.getKey()) : entry.getKey() != null) return false;
        return getValue() != null ? getValue().equals(entry.getValue()) : entry.getValue() == null;
    }

    @Override
    public int hashCode() {
        {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }
    }
}
