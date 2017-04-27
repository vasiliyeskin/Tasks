package com.javarush.task.task27.task2712.kitchen;


public enum Dish
{
    Fish(25), Steak(30), Soup(15), Juice(5), Water(3);
    private int duration;

    public int getDuration()
    {
        return duration;
    }

    Dish(int duration)
    {
        this.duration = duration;
    }

    public static String allDishesToString()
    {
        String sreturn = "";
        for(Dish d: Dish.values())
        {
            sreturn += d.name() + ", ";
        }
        sreturn = sreturn.substring(0, sreturn.length() - 2);

        return sreturn;
    }
}
