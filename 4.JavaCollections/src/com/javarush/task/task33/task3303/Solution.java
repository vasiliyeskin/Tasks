package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import com.fasterxml.jackson.annotation.*;

/* 
Десериализация JSON объекта
*/
public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        T cls = objectMapper.readValue(new File(fileName), clazz);
        return cls;
    }

    public static void main(String[] args) throws IOException {
            Cat cat = convertFromJsonToNormal("E:\\t2.txt", Cat.class);
            System.out.println(cat);
        }

        @JsonAutoDetect
        public static class Cat{
            public String name;
            public int age;

            Cat(){}

            @Override
            public String toString()
            {
                return "Cat{" +
                        "name='" + name + '\'' +
                        ", age=" + age +
                        '}';
            }
        }
}
