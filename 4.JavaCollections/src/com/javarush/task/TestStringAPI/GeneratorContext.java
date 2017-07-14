package com.javarush.task.TestStringAPI;


@FunctionalInterface
public interface GeneratorContext<T> {
    void emit(T value);
}
