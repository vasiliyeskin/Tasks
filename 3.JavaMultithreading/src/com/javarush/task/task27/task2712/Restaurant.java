package com.javarush.task.task27.task2712;


import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant
{
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args)
    {
        Locale.setDefault(Locale.ENGLISH);
        List<Tablet> tablets = new ArrayList<>();
        Cook cook1 = new Cook("Amigo");
        cook1.setQueue(orderQueue);
        Cook cook2 = new Cook("Bob");
        cook2.setQueue(orderQueue);


        Waiter waitor = new Waiter();
        cook1.addObserver(waitor);
        cook2.addObserver(waitor);
        for(int i = 0; i < 5; i++)
        {
            Tablet nTablet = new Tablet(i+1);
            nTablet.setQueue(orderQueue);
            tablets.add(nTablet);
        }

        Thread threadAmigo = new Thread(cook1);
        Thread threadChief = new Thread(cook2);
        threadAmigo.start();
        threadChief.start();



        RandomOrderGeneratorTask generator = new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL);
        Thread thread = new Thread(generator);
        thread.start();
        try
        {
            Thread.sleep(3000);
        }
        catch(InterruptedException e)
        {}
        thread.interrupt();
        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }

    /*
    public static void main(String[] args)
    {
        Locale.setDefault(Locale.ENGLISH);

        Tablet tablet = new Tablet(5);
        Cook amigo = new Cook("Amigo");
        Waiter Waiter = new Waiter();
        amigo.addObserver(Waiter);
        tablet.addObserver(amigo);
        tablet.createOrder();
        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
        Cook cook2=new Cook("mAmigo");
        Tablet tablet2=new Tablet(5);
        Waiter waiter2=new Waiter();
        cook2.addObserver(waiter2);
        tablet2.addObserver(cook2);
        tablet2.createOrder();
        directorTablet=new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
        Cook cook3=new Cook("Am");
        Tablet tablet3=new Tablet(5);
        Waiter waiter3=new Waiter();
        cook3.addObserver(waiter3);
        tablet3.addObserver(cook3);
        tablet3.createOrder();
        directorTablet=new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }*/
}
