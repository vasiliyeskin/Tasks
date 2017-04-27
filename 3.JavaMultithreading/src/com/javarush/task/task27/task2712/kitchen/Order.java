package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

/**
 * Created by Vaisiliy Es'kin on 12/29/16.
 */
public class Order
{
    private List<Dish> dishes;
    private Tablet tablet;

    public Order(Tablet t) throws IOException
    {
            this.dishes = ConsoleHelper.getAllDishesForOrder();
            this.tablet = t;
    }

    public int getTotalCookingTime()
    {
        int time = 0;
        for (int i = 0; i < dishes.size(); i++)
        {
            time += dishes.get(i).getDuration();
        }
        return time;
    }

    public boolean isEmpty()
    {
        return dishes.isEmpty();
    }

    @Override
    public String toString()
    {
        String s = "";

        if(dishes.size() > 0)
        {
            return String.format("Your order: %1$s of %2$s", this.dishes.toString(), tablet.toString());
        }
        return s;
    }

    public List<Dish> getDishes()
    {
        return dishes;
    }

    public Tablet getTablet()
    {
        return tablet;
    }
}
