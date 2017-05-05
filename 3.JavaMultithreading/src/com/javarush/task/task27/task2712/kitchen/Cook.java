package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;


public class Cook extends Observable
{
    private String name;

    public Cook(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }

    /*public void startCookingOrder(Order order)
    {
        Order order = (Order) arg;
        System.out.println("Start cooking - " + arg);
        setChanged();
        notifyObservers(arg);

        System.out.println(String.format("Start cooking - %1$s, cooking time %2$dmin", arg.toString(), ((Order) arg).getTotalCookingTime()));
        StatisticManager.getInstance().
                register(new CookedOrderEventDataRow(order.getTablet().toString(),
                        name,
                        order.getTotalCookingTime()*60,
                        order.getDishes()));
    }*/

    public void startCookingOrder(Order order) {
        ConsoleHelper.writeMessage(String.format("Start cooking - %s, cooking time %dmin", order, order.getTotalCookingTime()));
        StatisticManager.getInstance()
                .register(new CookedOrderEventDataRow(order.getTablet().toString(), name,
                        order.getTotalCookingTime() * 60, order.getDishes()));
        setChanged();
        notifyObservers(order);
    }

}
