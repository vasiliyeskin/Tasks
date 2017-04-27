package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {


    public static Map <ZipEntry, byte[]> entryMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        File zipArchive = new File(args[1]);
        File addedFile = new File(args[0]);

        zipToMap(zipArchive);
        addNewFileToZIP(addedFile, zipArchive);
    }


    public static void zipToMap(File file)  {

        // Записываем содержимое архива в карту
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file))) {

            ZipEntry zipEntry;

            // перебираем все zipEntries
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                byte[] buffer = new byte[1024];
                int count;

                while ((count = zipInputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, count);
                }
                byte[] bytes = byteArrayOutputStream.toByteArray();
                entryMap.put(zipEntry, bytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void addNewFileToZIP(File addedFile, File zipArchive) {

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipArchive));
             FileInputStream fileInputStream = new FileInputStream(addedFile)) {

            //Маркер наличия добавляемого файла в архиве
            boolean isExist = false;

            //Сохраняем сразу в отдельный zipEntry добавляемый файл для последующего сравнения
            ZipEntry fileNameComparator = new ZipEntry(addedFile.getName());

            zipOutputStream.putNextEntry(new ZipEntry("new" +  File.separator  + addedFile.getName()));
            Files.copy(Paths.get(addedFile.getAbsolutePath()), zipOutputStream);


            //Копируем zipEntry с entryMap в архив
            for (Map.Entry<ZipEntry, byte[]> zipEntry : entryMap.entrySet()) {

                //Подрезаем путь файла для сравненияс добавляемым файлом
                Path path = Paths.get(zipEntry.getKey().getName());

                //Сравниваем...
                //Если имя текущего файла в zipEntry НЕ совпадает с добавлемым файлом
                if(!(path.getFileName().toString().equals(fileNameComparator.getName()))) {
                    //Записываем в архив
                    zipOutputStream.putNextEntry(new ZipEntry(zipEntry.getKey().getName()));
                    zipOutputStream.write(zipEntry.getValue());

                }
                //Если же файл с таким названием присутствует в архиве...
                else {
                    isExist = true;
                }
            }

            //Если в процессе прохождения цикла в архиве нашелся файл с таким-же именем как и добавляемый..
           /*  if (isExist)
            {
                zipOutputStream.putNextEntry(new ZipEntry("new" +  File.separator  + addedFile.getName()));
                Files.copy(Paths.get(addedFile.getAbsolutePath()), zipOutputStream);

               //Добавляем файл в папку new
                ZipEntry addingFileEntry = new ZipEntry("new" +  File.separator  + addedFile.getName());
                zipOutputStream.putNextEntry(addingFileEntry);

                //Считываем содержимое файла в массив byte
                byte[] buffer = new byte[fileInputStream.available()];
                fileInputStream.read(buffer);

                //Добавляем содержимое к архиву
                zipOutputStream.write(buffer);
                //Закрываем текущую запись для новой записи
                zipOutputStream.closeEntry();


              //  Files.copy(Paths.get(addedFile.getAbsolutePath()), zipOutputStream);

            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





/*
        String fileName = args[0];
        String zipName = args[1];

        Map<String, byte[]> tempZipEntries = new HashMap<>();
        byte[] buffer = new byte[2048];
        ByteArrayOutputStream baos;
        int len = 0;

        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipName));
        ZipEntry zipEntry;
        while ((zipEntry = zis.getNextEntry()) != null)
        {
            String entryName = zipEntry.getName();
            baos = new ByteArrayOutputStream();
            while ((len = zis.read(buffer)) > 0)
            {
                baos.write(buffer, 0, len);
            }
            tempZipEntries.put(entryName, baos.toByteArray());
        }
        zis.close();

        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        baos = new ByteArrayOutputStream();
        while ((len = fis.read(buffer)) > 0)
        {
            baos.write(buffer, 0, len);
        }
        String shortFileName = file.getName();
        String args0FileName = "new/" + shortFileName;


        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipName));

        ZipEntry zipEntryFile;
        if(tempZipEntries.containsKey(shortFileName))
            zipEntryFile = new ZipEntry(shortFileName);
        else
            zipEntryFile = new ZipEntry(args0FileName);

        zos.putNextEntry(zipEntryFile);
        zos.write(baos.toByteArray());

        for (Map.Entry<String, byte[]> entry : tempZipEntries.entrySet())
        {
            if(!zipEntryFile.getName().equals(entry.getKey())) {
                zos.putNextEntry(new ZipEntry(entry.getKey()));
                zos.write(entry.getValue());
                zos.closeEntry();
            }
        }
        zos.close();
    }*/
}
