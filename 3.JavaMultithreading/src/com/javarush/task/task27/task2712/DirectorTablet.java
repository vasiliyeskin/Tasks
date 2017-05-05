package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

    public void printAdvertisementProfit() {
        Map<Date, Long> map = StatisticManager.getInstance().getStatisticForShownAdvertisement();
        long totalAmount = 0;

        for (Map.Entry<Date, Long> entry : map.entrySet()) {
            totalAmount += entry.getValue();
            ConsoleHelper.writeMessage(String.format("%s - %.2f",dateFormat.format(entry.getKey()), entry.getValue()*0.01d));
        }
        ConsoleHelper.writeMessage(String.format("Total - %.2f",  totalAmount*0.01d));
        ConsoleHelper.writeMessage("");
    }

    public void printCookWorkloading()
    {
        Map<Date, Map<String, Integer>> map = StatisticManager.getInstance().getStatisticForCooks();

        for (Map.Entry<Date, Map<String, Integer>> entry1 : map.entrySet()) {
            ConsoleHelper.writeMessage(dateFormat.format(entry1.getKey()));
            for (Map.Entry<String, Integer> entry2 : entry1.getValue().entrySet()) {
                ConsoleHelper.writeMessage(entry2.getKey() + " - " + entry2.getValue() + " min");
            }
            ConsoleHelper.writeMessage("");
        }
        ConsoleHelper.writeMessage("");
    }

    public void printActiveVideoSet() {
        if (StatisticAdvertisementManager.getInstance().getAllAdvertisement().isEmpty()) return;
        for (Map.Entry<String, Integer> dt : StatisticAdvertisementManager.getInstance().getAllAdvertisement().entrySet()){
            if (dt.getValue() > 0) ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %d", dt.getKey(), dt.getValue()));
        }
        //ConsoleHelper.writeMessage("");
    }
    public void printArchivedVideoSet() {
        if (StatisticAdvertisementManager.getInstance().getAllAdvertisement().isEmpty()) return;
        for (Map.Entry<String, Integer> dt : StatisticAdvertisementManager.getInstance().getAllAdvertisement().entrySet()){
            if (dt.getValue() <= 0) ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, dt.getKey()));
        }
        //ConsoleHelper.writeMessage("");
    }

    private List<String> outPutList = new ArrayList<>();

    public List<String> wPrintAdvertisementProfit() {
        outPutList = new ArrayList<>();
        printAdvertisementProfit();
        return outPutList;
    }

    public List<String> wPrintCookWorkloading() {
        outPutList = new ArrayList<>();
        printCookWorkloading();
        return outPutList;
    }
}
