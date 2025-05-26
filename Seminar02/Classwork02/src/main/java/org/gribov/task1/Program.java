package org.gribov.task1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Program {
    /**
     * Задача 1: основы Reflection API
     * Получите информацию о классе "Person" c использованием reflection API:
     * выведите на экран все поля и методы класса
     * Создайте экземпляр класса "Person" c использованием Reflection API,
     * установите значения полей и вызовите методы.
     * Реализуйте обработку исключений для обеспечения корректного взаимодействия
     * с Reflection API.
     */

    public static void main(String[] args) throws ClassNotFoundException,
            NoSuchFieldException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException,
            NoSuchMethodException {
        //personalClass - описатель типа
        Class<?> personalClass = Class.forName("org.gribov.task1.Person");

        //Получить список всех полей
        Field[]fields = personalClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("Поле: " + field.getName());
        }

        //Получить список всех конструкторов
        Constructor[] constructors = personalClass.getConstructors();
        System.out.println(Arrays.toString(constructors[0].getParameters()));


        //Создадим экземпляр класса
        Object personInstance = constructors[0].newInstance(null);

        Field nameField = personalClass.getDeclaredField("name");
        Field ageField = personalClass.getDeclaredField("age");

        //Изменим значение поля
        nameField.setAccessible(true);
        nameField.set(personInstance, "Alice");

        //Изменим значение поля
        ageField.setAccessible(true);
        ageField.set(personInstance, 30);


        //Получим метод
        Method displayInfoMethod = personalClass.getDeclaredMethod("displayInfo");
        displayInfoMethod.invoke(personInstance); //вызов метода
    }

}
