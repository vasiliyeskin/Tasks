package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper
{
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message)
    {
        System.out.println(message);
    }

    public static String readString() throws IOException
    {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException
    {
        List<Dish> ldishes = new ArrayList<>();
        writeMessage("Enter dish...(" + Dish.allDishesToString() + ")");
        while(true)
        {
            String s = readString();
            if("exit".equals(s.toLowerCase()))
            break;
            try
            {
                ldishes.add(Dish.valueOf(s));
            }
            catch (IllegalArgumentException e)
            {
                ConsoleHelper.writeMessage(s + " is not detected");
            }
        }

        return ldishes;
    }
}
