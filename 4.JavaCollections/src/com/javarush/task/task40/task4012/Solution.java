package com.javarush.task.task40.task4012;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/* 
Полезные методы DateTime API
*/

public class Solution {
    public static void main(String[] args) {

    }

    public static boolean isLeap(LocalDate date) {
        return date.isLeapYear();
    }

    public static boolean isBefore(LocalDateTime dateTime) {
        return dateTime.isBefore(LocalDateTime.now());
    }

    public static LocalTime addTime(LocalTime time, int n, ChronoUnit chronoUnit) {
        time = time.plus(n, chronoUnit);
        return time;
    }

    public static Period getPeriodBetween(LocalDate firstDate, LocalDate secondDate) {
        Period p;
        if(firstDate.isBefore(secondDate))
            p = Period.between(firstDate,secondDate);
        else
            p = Period.between(secondDate,firstDate);

        return p;
    }
}
