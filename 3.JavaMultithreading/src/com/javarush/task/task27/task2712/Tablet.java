package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Tablet extends Observable {
    final static Logger logger = Logger.getLogger(Tablet.class.getName());
    private final int number;

    public Tablet(int number) {
        this.number = number;
    }

    public void createOrder()
    {
        Order order = null;
        try
        {
            order = new Order(this);
            initOrder(order);
        }
        catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }
    public void createTestOrder()
    {
        Order order = null;
        try
        {
            order = new TestOrder(this);
            initOrder(order);
        }
        catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }
    private void initOrder(Order order)
    {
        if (!order.isEmpty()) {
            ConsoleHelper.writeMessage(order.toString());
            setChanged();
            notifyObservers(order);
        }
        try
        {
            new AdvertisementManager(order.getTotalCookingTime() * 60).processVideos();
        }catch (NoVideoAvailableException e) {
            logger.log(Level.INFO, "No video is available for the order " + order);
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Tablet{number=" + number + "}";
    }
}