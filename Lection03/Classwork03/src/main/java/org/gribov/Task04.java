package org.gribov;

import java.io.*;

public class Task04 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //экземпляр
        MyFCsExt myFCsExt = new MyFCsExt("Petrov", "Petr", "Petrovich");
        //сериализация
        serialObj(myFCsExt, "serClassExt");

        //десериализация
        MyFCsExt myFCsExt1 = (MyFCsExt) deSerialObj("serClassExt");
        System.out.println(myFCsExt1);
    }



    /**
     * Метод сериализации
     *
     * @param o    объект, который требуется сериализовать (строка итд)
     * @param file файл в который выполняется сериализация
     * @throws IOException исключение записи в файл
     */
    public static void serialObj(Object o, String file) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(file);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(o);

        objectOutputStream.close();
    }

    /**
     * Метод десериализации
     *
     * @param file файл из которого выполняется десериализация
     * @return возвращается объект
     * @throws IOException            исключение чтения из файла
     * @throws ClassNotFoundException исключение чтение объекта из потока
     */
    public static Object deSerialObj(String file) throws IOException, ClassNotFoundException {

        FileInputStream fileInputStream = new FileInputStream(file);

        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        return objectInputStream.readObject();
    }

}
