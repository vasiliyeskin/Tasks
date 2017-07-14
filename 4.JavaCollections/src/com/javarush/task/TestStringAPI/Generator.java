package com.javarush.task.TestStringAPI;

@FunctionalInterface
public interface Generator<T> {
    void generate(GeneratorContext<T> context);
}
