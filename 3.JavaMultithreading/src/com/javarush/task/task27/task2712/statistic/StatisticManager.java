package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;


public class StatisticManager
{
    private static StatisticManager ourInstance = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();

    public Set<Cook> cooks = new HashSet<Cook>();

    public static StatisticManager getInstance()
    {
        if (ourInstance == null)
        {
            ourInstance = new StatisticManager();
        }
        return ourInstance;
    }

    private StatisticManager()
    {
    }

    public void register(EventDataRow data)
    {
        if (data == null) return;
        statisticStorage.put(data);
    }

    public void register(Cook cook)
    {
        this.cooks.add(cook);
    }

    private class StatisticStorage
    {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        private StatisticStorage() {
            for (EventType eventType : EventType.values()) {
                storage.put(eventType, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data)
        {
            if (data == null) return;
            storage.get(data.getType()).add(data);
        }

        private Map<EventType, List<EventDataRow>> getData()
        {
            return storage;
        }
    }

    public Map<Date, Long> getStatisticForShownAdvertisement()
    {
        Map<EventType, List<EventDataRow>> storageMap = statisticStorage.getData();
        List<EventDataRow> list = storageMap.get(EventType.SELECTED_VIDEOS);

        Map<Date, Long> mapDate = new TreeMap<>(Collections.reverseOrder());

        for (EventDataRow event : list)
        {
            VideoSelectedEventDataRow videoSelectedEvent = (VideoSelectedEventDataRow) event;
            Long amount = videoSelectedEvent.getAmount();

            Date date = dateToStringMidnight(videoSelectedEvent.getDate());

            if (mapDate.containsKey(date))
            {
                mapDate.put(date, mapDate.get(date) + amount);
            } else
            {
                mapDate.put(date, amount);
            }
        }

        return mapDate;
    }

    public Map<Date, Map<String, Integer>> getStatisticForCooks()
    {
        Map<EventType, List<EventDataRow>> storageMap = statisticStorage.getData();
        List<EventDataRow> list = storageMap.get(EventType.COOKED_ORDER);


        Map<Date, Map<String, Integer>> map = new TreeMap<>(Collections.reverseOrder());

        for (EventDataRow event : list)
        {
            CookedOrderEventDataRow cookedOrderEvent = (CookedOrderEventDataRow) event;
            Date date = dateToStringMidnight(cookedOrderEvent.getDate());
            String cookName = cookedOrderEvent.getCookName();
            int cookingTime = cookedOrderEvent.getTime();
            if (cookingTime == 0) continue;
            int cookingTimeMin = (cookingTime % 60 == 0) ? (cookingTime / 60) : (cookingTime / 60 + 1);

            if (map.containsKey(date))
            {
                Map<String, Integer> temp = map.get(date);
                if (temp.containsKey(cookName))
                {
                    temp.put(cookName, temp.get(cookName) + cookingTimeMin);
                } else
                {
                    temp.put(cookName, cookingTimeMin);
                }
                map.put(date, temp);
            } else
            {
                Map<String, Integer> temp = new TreeMap<>();
                temp.put(cookName, cookingTimeMin);
                map.put(date, temp);
            }
        }
        return map;
    }

    private Date dateToStringMidnight(Date date) //приведение даты к полночи
    {
        GregorianCalendar roundedDate = new GregorianCalendar();
        roundedDate.setTime(date);
        roundedDate.set(Calendar.HOUR_OF_DAY, 0);
        roundedDate.set(Calendar.MINUTE, 0);
        roundedDate.set(Calendar.SECOND, 0);
        roundedDate.set(Calendar.MILLISECOND, 0);
        return roundedDate.getTime();
    }
}
