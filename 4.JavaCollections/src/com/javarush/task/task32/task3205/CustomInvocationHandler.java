package com.javarush.task.task32.task3205;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Vaisiliy Es'kin on 02/26/17.
 */
public class CustomInvocationHandler implements InvocationHandler {
    private SomeInterfaceWithMethods siwm;

    public CustomInvocationHandler(SomeInterfaceWithMethods siwm) {
        this.siwm = siwm;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println(method.getName() + " in");

        Object o = method.invoke(siwm, args);

        System.out.println(method.getName() + " out");

        return o;
    }
}
