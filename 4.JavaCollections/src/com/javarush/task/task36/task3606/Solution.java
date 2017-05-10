package com.javarush.task.task36.task3606;

import sun.reflect.Reflection;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        File dir = new File(packageName + File.separator);
        final String filePath = dir.getAbsolutePath() + File.separator;

        String[] classes = dir.list() ;

        ClassLoader classLoader = new ClassLoader() {
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {

                byte b[] = new byte[0];
                try {
                   // b = Files.readAllBytes(Paths.get(((packageName.startsWith("/") ? packageName.substring(1) : packageName) + "/") + name + ".class"));
                    b = Files.readAllBytes(Paths.get(filePath + name + ".class"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return defineClass(null, b, 0, b.length);
            }
        };

        for(String s: classes)
        {
            if(s.endsWith(".class"))
            {
                Class cl = classLoader.loadClass(s.split(".class")[0]);
                if(HiddenClass.class.isAssignableFrom(cl))
                {
                    hiddenClasses.add(cl);
                }
            }
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        for(Class c: hiddenClasses)
        {
            if(c.getSimpleName().toLowerCase().startsWith(key.toLowerCase()))
            {
                try {
                    Constructor[] constructors = c.getDeclaredConstructors();
                    for (Constructor constructor : constructors) {
                        if (constructor.getParameterTypes().length == 0) {
                            constructor.setAccessible(true);
                            return (HiddenClass) constructor.newInstance(null);
                        }
                    }
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }
}

