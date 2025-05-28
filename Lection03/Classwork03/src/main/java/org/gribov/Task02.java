package org.gribov;

import java.io.*;
import java.util.ArrayList;

/*todo
    Давайте создадим несколько методов для упрощения последующих
    шагов.
 */
public class Task02 {


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


    /**
     * Давайте перейдем к усложнению объекта!
     * Проведём сериализацию листа list.
     * Проведём десериализацию.
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Заполнил list строковыми названиями (используя метод класса Character)
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            //Character.getName() — статический метод класса Character в Java.
            //Он возвращает имя символа, соответствующего заданному коду точки Unicode.
            list.add(Character.getName(i));

        }
        //сериализация
        serialObj(list, "ser");


        //десериализация
        ArrayList<String> newList = (ArrayList<String>) deSerialObj("ser");
        System.out.println(newList);


    }
}
