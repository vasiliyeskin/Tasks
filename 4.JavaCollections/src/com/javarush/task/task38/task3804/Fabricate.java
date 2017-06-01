package com.javarush.task.task38.task3804;

/**
 * Created by Vaisiliy Es'kin on 06/01/17.
 */
public class Fabricate {
    public static Throwable getException(Enum enumeration) {
        if (enumeration == null)
            return new IllegalArgumentException();

        String message = enumeration.name().charAt(0) + enumeration.name().substring(1).toLowerCase().replace("_", " ");

        if (enumeration instanceof ExceptionApplicationMessage)
            return new Exception(message);

        if (enumeration instanceof ExceptionDBMessage)
            return new RuntimeException(message);

        if (enumeration instanceof ExceptionUserMessage)
            return new Error(message);

        return new IllegalArgumentException();
    }
}
