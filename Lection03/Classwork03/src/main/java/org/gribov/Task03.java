package org.gribov;

/*todo
    Давайте попробуем выполнить процессы сериализации и десериализации
    для объекта, созданного на основе этого нового класса MyFCs.
 */

import java.io.*;

public class Task03 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //экземпляр
        MyFCs myFCs = new MyFCs("Ivanov", "Ivan", "Ivanovich");
        //сериализация
        serialObj(myFCs, "serClass");

        //десериализация
        MyFCs myFCs1 = (MyFCs) deSerialObj("serClass");
        System.out.println(myFCs1);
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
