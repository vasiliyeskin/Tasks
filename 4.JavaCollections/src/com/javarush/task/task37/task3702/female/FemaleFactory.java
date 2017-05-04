package com.javarush.task.task37.task3702.female;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;

import java.util.AbstractSet;


/**
 * Created by Vaisiliy Es'kin on 05/04/17.
 */
public class FemaleFactory implements AbstractFactory {

    public Human getPerson(int age)
    {
        if(age <= KidGirl.MAX_AGE)
            return new KidGirl();
        if(age <= TeenGirl.MAX_AGE)
            return new TeenGirl();

        return new Woman();

    }

}
