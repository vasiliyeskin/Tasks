package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
"C:/result.mp3" "C:/pathToTest/test.zip.003" "C:/pathToTest/test.zip.001" "C:/pathToTest/test.zip.004" "C:/pathToTest/test.zip.002"
"E:\test\m2.pdf" "E:\test\metodichka.zip" "E:\test\metodichka.z03" "E:\test\metodichka.z02" "E:\test\metodichka.z01"
*/
public class Solution {
    public static void main(String[] args) {
        String resultFileName = args[0];
        String[] fileNamePart = new String[args.length - 1];

        for (int i = 0; i < fileNamePart.length; i++) {
            fileNamePart[i] = args[i+1];

        }

        Arrays.sort(fileNamePart);

        try {

            List<FileInputStream> fisList = new ArrayList<>();
            for (int i = 0; i < fileNamePart.length; i++) {
                fisList.add(new FileInputStream(fileNamePart[i]));
            }

            SequenceInputStream seqInStream = new SequenceInputStream(Collections.enumeration(fisList));

            ZipInputStream zipInStream = new ZipInputStream(seqInStream);
            FileOutputStream fileOutStream = new FileOutputStream(resultFileName);
            byte[] buf = new byte[1024 * 1024]; // 1MB buffer
            while (zipInStream.getNextEntry() != null) {

                int count;
                while ((count = zipInStream.read(buf)) != -1) {
                    fileOutStream.write(buf, 0, count);
                }

            }
            seqInStream.close();
            zipInStream.close();
            fileOutStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
