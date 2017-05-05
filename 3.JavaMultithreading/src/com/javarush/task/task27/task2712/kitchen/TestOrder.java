package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;

public class TestOrder extends Order {
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() throws IOException {
        dishes = new ArrayList<>();
        int dishLength = Dish.values().length;
        int randomLength = (int) (Math.random() * dishLength) + 1;
        for (int i = 0; i < randomLength; i++) {
            double random = Math.random() * dishLength;
            dishes.add(Dish.values()[(int) random]);
        }
    }
}


/*
public class TestOrder extends Order
{
    public TestOrder(Tablet tablet) throws IOException
    {
        super(tablet);
    }

    @Override
    protected void initDishes()
    {
        ConsoleHelper.writeMessage(Dish.allDishesToString());
        dishes = new ArrayList<>();
        int randDishCount = (int)(Math.random() * Dish.values().length);
        dishes.add(Dish.values()[randDishCount]);
        randDishCount = (int)(Math.random() * Dish.values().length);
        dishes.add(Dish.values()[randDishCount]);
        randDishCount = (int)(Math.random() * Dish.values().length);
        dishes.add(Dish.values()[randDishCount]);

    }
}*/
