package com.javarush.task.task33.task3305;

import com.fasterxml.jackson.annotation.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,  property="className")
/*@JsonSubTypes({
        @JsonSubTypes.Type(value=RaceBike.class, name="com.javarush.task.task33.task3305.RaceBike"),
        @JsonSubTypes.Type(value=Motorbike.class, name="com.javarush.task.task33.task3305.Motorbike"),
        @JsonSubTypes.Type(value=Car.class, name="com.javarush.task.task33.task3305.Car")})*/
public abstract class Auto {
    protected String name;
    protected String owner;
    protected int age;
}