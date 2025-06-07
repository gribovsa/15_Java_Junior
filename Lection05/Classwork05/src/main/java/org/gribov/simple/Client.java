package org.gribov.simple;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Создадим класс Client.
 * Для примера я воспользовался конструктором требующем InetAddress. Сам по себе
 * он не требует конструктора, так как он возвращает не новый ip-адрес сервера, а
 * адрес существующего. Для этого служит статический метод getLocalHost()
 * возвращающий локальный адрес.
 */

public class Client {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getLocalHost();
            Socket client = new Socket(address, 1300);

            //Метод getInetAddress() возвращает имя компьютера и адрес в сети
            System.out.println(client.getInetAddress());
            //метод getLocalPart() соответственно возвращает порт для нашего конкретного подключения
            System.out.println(client.getLocalPort());


            //Первые две строки, это собственно потоки.
            // Метод getInputStream() возвращает поток ввода клиентского сокета а
            // getOutputStream() соответственно поток вывода.
            InputStream inStream = client.getInputStream();
            OutputStream outStream = client.getOutputStream();


            //Класс DataInputStream позволяет читать из потока любые примитивные типы данных, массивы и строки!
            DataInputStream dataInputStream = new DataInputStream(inStream);
            //Класс PrintStream добавляет функционал потоку, фактически позволяя нам просто печатать туда строки
            //как будто это обычный print.
            PrintStream printStream = new PrintStream(outStream);


            //Первая трока, как раз и показывает возможности класса PrintStream, передача
            //строки по сети свелось к вызову классического принта с переносом строки ( println()).
            printStream.println("Привет!");
            //Вторая строка использует возможности класса DataInputStream, чтобы получить
            //по сети строку и сразу вывести её в консоль.
            System.out.println(dataInputStream.readLine());

            //Ну и в третьей строке мы закрываем сокет.
            client.close();




        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
