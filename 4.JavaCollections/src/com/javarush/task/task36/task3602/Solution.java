package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        for(Class c : Collections.class.getDeclaredClasses())
        {
            if(List.class.isAssignableFrom(c)
                    && Modifier.isPrivate(c.getModifiers())
                    && Modifier.isStatic(c.getModifiers())) {

                try {
                    Constructor constructor = c.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    List l = (List) constructor.newInstance();
                    l.get(0);
                } catch (NoSuchMethodException e) {
                } catch (InstantiationException e) {
                } catch (IllegalAccessException e) {
                } catch (InvocationTargetException e) {
                }
                catch (IndexOutOfBoundsException e)
                {
                    return c;
                }

            }
        }

        return null;
    }
}
