package com.javarush.task.TestStringAPI;

import java.util.*;
import java.util.function.Consumer;

public class MAIN {
    public static void main(String... args) {
        Dataset.of(Arrays.asList("шла", "саша", "по", "шоссе"))
                .union(Arrays.asList("и", "сосала", "сушку"))
                .filter(s -> s.length() > 4)
                .map(s -> s + ", length=" + s.length())
                .forEach(System.out::println);

        Dataset.of(Arrays.asList("foo", "bar")).forEach(System.out::println);




        class Person{
            String firstName, lastname;

            public Person(String firstName, String lastname) {
                this.firstName = firstName;
                this.lastname = lastname;
            }
        }
        Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
        greeter.accept(new Person("Luke", "Skywalker"));


    }
}
