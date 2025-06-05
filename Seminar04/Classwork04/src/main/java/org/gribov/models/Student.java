package org.gribov.models;

import javax.persistence.*;
import java.util.Random;


//для Hibernate (указание сущности и название таблицы)
@Entity
@Table(name = "students")

public class Student {


    private static final String[] names = new String[] { "Анатолий", "Глеб", "Клим", "Мартин", "Лазарь", "Владлен", "Клим", "Панкратий", "Рубен", "Герман" };
    private static final Random random = new Random();


    //для Hibernate (уникальный идентификатор)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int age;

    //конструктор по умолчанию, требуется для Hibernate
    public Student() {

    }

    //region Конструкторы
    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    //endregion


    /**
     * Метод для рандомного создания студента
     * @return возвращает объект Student (имя, возраст)
     */
    public static Student create(){
        return new Student(names[random.nextInt(names.length)], random.nextInt(20, 26));
    }


    //region Вспомогательные методы
    public void updateAge(){
        age = random.nextInt(20, 26);
    }

    public void updateName(){
        name = names[random.nextInt(names.length)];
    }
    //endregion


    // region Геттеры и Сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    //endregion


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
