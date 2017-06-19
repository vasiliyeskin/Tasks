package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.command.ExitCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

/**
 * Created by Vaisiliy Es'kin on 06/18/17.
 */
public class Archiver {

    public Archiver() {
    }

    public static void main(String[] args) {
        System.out.println("Input path to zip file");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = "";
        try {
            file = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ZipFileManager zfm = new ZipFileManager(Paths.get(file));

        System.out.println("Input path to file prepared to archiv");

        try {
            zfm.createZip(Paths.get(reader.readLine()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            new ExitCommand().execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
