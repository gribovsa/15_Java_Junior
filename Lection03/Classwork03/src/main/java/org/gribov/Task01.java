package org.gribov;

/*todo
    Сериализация
        1. Создание потока записи байт в файл.
        2. Создание потока записи объекта в файл.
        3. Запись объекта.
        4. Закрытие потоков.
 */

import java.io.*;

public class Task01 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //В этом примере мы создаем строку и сохраняем ее в файл
        String str = "Hello, everyone!";

        //создаем поток записи байт в файл
        FileOutputStream fileOutputStream = new FileOutputStream("serialized_object.txt");

        //поток записи объекта
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        //выполняем запись объекта
        objectOutputStream.writeObject(str);

        //закрываем потоки
        objectOutputStream.close();

        //После записи мы освобождаем ресурсы, чтобы файл был читаемым.
        //В результате получается бинарный файл, который содержит сериализованный
        //объект.




        //Теперь, если даже десериализация не кажется сложной, давайте рассмотрим ее на
        //примере:

        //мы создаем поток чтения байт из файла
        FileInputStream fileInputStream = new FileInputStream("serialized_object.txt");

        //поток чтения объекта
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        //выполняем чтение объекта
        //загружаем именно объект типа Object, и мы сами выполняем его приведение к нужному типу
        String loadedString = (String) objectInputStream.readObject();

        //закрываем потоки
        objectInputStream.close();

        //выводим
        System.out.println(loadedString);




























    }
}
