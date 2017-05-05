package com.javarush.task.task27.task2712;


import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Restaurant
{
    private static final int ORDER_CREATING_INTERVAL = 100;

    public static void main(String[] args)
    {
        Locale.setDefault(Locale.ENGLISH);
        List<Tablet> tablets = new ArrayList<>();
        Cook cook1 = new Cook("Amigo");
        Cook cook2 = new Cook("Bob");
        Waiter waitor = new Waiter();
        cook1.addObserver(waitor);
        cook2.addObserver(waitor);
        for(int i = 0; i < 10; i++)
        {
            tablets.add(new Tablet(i+1));
        }
        for (int i=0; i < tablets.size(); i++)
        {
            if(i%2 == 0) tablets.get(i).addObserver(cook1);
            else tablets.get(i).addObserver(cook2);
        }
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
