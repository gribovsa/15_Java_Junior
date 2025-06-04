package org.gribov.task01;

import java.io.*;

public class Program {

    /**
     * Задача 1.
     * Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
     * Обеспечьте поддержку сериализации для этого класса.
     * Создайте объект класса Student и инициализируйте его данными.
     * Сериализируйте этот объект в файл. Десериализируйте объект обратно в программу из файла.
     * Выведите все поля объекта, включая GPA, и обсудите, почему значение GPA не было сохранено/восстановлено.
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student user1 = new Student("Алексей", 22, 5.0);
        Student user2 = new Student("Николай", 19, 4.9);

        try (FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/Home_task_1.bin");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(user1);
            System.out.println("\nОбъект User1 сериализирован.");
            objectOutputStream.writeObject(user2);
            System.out.println("Объект User2 сериализирован");
        }

        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/Home_task_1.bin");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            user1 = (Student) objectInputStream.readObject();
            System.out.println("\nОбъект User1 десериализирован.");
            user2 = (Student) objectInputStream.readObject();
            System.out.println("Объект User2 десериализирован.");
        }

        System.out.println("\nОбъект User1:");
        System.out.println("Имя:          " + user1.getName());
        System.out.println("Возраст:      " + user1.getAge());
        System.out.println("Средний балл: " + user1.getGPA());

        System.out.println("\nОбъект User2:");
        System.out.println("Имя:          " + user2.getName());
        System.out.println("Возраст:      " + user2.getAge());
        System.out.println("Средний балл: " + user2.getGPA());

    /*
    Примечание:
    Данные о среднем балле не были сохранены в файл, а в дальнейшем не были восстановлены потому,
    что переменная GPA (средний балл) имеет transient-свойство;
    */

    }
}
