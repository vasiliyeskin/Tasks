package com.javarush.task.task33.task3310.strategy;

import com.javarush.task.task33.task3310.Helper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Vaisiliy Es'kin on 05/11/17.
 */
public class FileBucket {
    Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile(null, null);
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
        }

        path.toFile().deleteOnExit();
    }

    public long getFileSize() {
        long size = 0;
        try {
            size = Files.size(path);
        } catch (IOException e) {
        }
        return size;
    }

    public void putEntry(Entry entry) {
        try {
            OutputStream os = Files.newOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(entry);
            oos.flush();
        } catch (IOException e) {
        }
    }

    public Entry getEntry() {
        Entry entry = null;

        if (getFileSize() == 0) return null;

        try {
            InputStream inputStream = Files.newInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            entry = (Entry) ois.readObject();
        } catch (Exception e) {
        }
        return entry;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException e) {
        }
    }

}
