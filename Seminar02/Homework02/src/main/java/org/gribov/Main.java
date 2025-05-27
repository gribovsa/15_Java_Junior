package org.gribov;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*todo
    Используя Reflection API, напишите программу, которая выводит на экран все методы класса String.
 */
public class Main {


    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {

        int counterMethod = 1;

        Class<?> myClass = Class.forName("java.lang.String");

        Method[] methods = myClass.getMethods();

        for (Method method : methods) {
            System.out.println("Метод №" + counterMethod + ": " + method + "\n");
            counterMethod++;
        }

        System.out.println("КЛАСС: " + myClass + " СОДЕРЖИТ: " + (counterMethod - 1) + " МЕТОДА.");
    }

}
