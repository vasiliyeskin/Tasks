package com.javarush.task.task29.task2912;

/* 
Рефакторинг паттерна Chain of Responsibility
*/

public class Solution {
    public static void main(String[] args) {
        Logger logger3 = new PhoneLogger(Level.FATAL);
        Logger logger2 = new SmsLogger(Level.ERROR);
        Logger logger1 = new ConsoleLogger(Level.WARN);
        Logger logger0 = new FileLogger(Level.INFO);

        logger3.setNext(logger2);
        logger2.setNext(logger1);
        logger1.setNext(logger0);

        logger3.inform("All OK", Level.INFO);
        logger3.inform("We find a bug", Level.WARN);
        logger3.inform("Database connection error", Level.ERROR);
        logger3.inform("System shut down", Level.FATAL);
    }
}

/*
Logging to file: All OK
Logging to console: We find a bug
Logging to file: We find a bug
Send sms to CEO: Database connection error
Logging to console: Database connection error
Logging to file: Database connection error
Call to director: System shut down
Send sms to CEO: System shut down
Logging to console: System shut down
Logging to file: System shut down
*/