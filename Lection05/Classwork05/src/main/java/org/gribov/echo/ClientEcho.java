package org.gribov.echo;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Модифицированный класс ClientEcho работает с ServerEcho
 */
public class ClientEcho {
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
            printStream.println("Hello!");
            //Вторая строка использует возможности класса DataInputStream, чтобы получить
            //по сети строку и сразу вывести её в консоль.
            System.out.println(dataInputStream.readLine());


            //======================================================
            //Мы создали строку ввода, чтобы хранить в ней строку на передачу.
            String inLine;
            //Сканер, как самый простой класс предназначенный для ввода текста с консоли.
            Scanner scanner = new Scanner(System.in);
            //Цикл while будет крутиться пока мы не введём ключевое слово «exit».
            while (!(inLine = scanner.nextLine()).equals("exit")){
                //В теле цикла мы просто посылаем серверу строку, а потом ждём ответа.
                printStream.println(inLine);
                System.out.println(dataInputStream.readLine());
            }
            //А если ввели «exit» то и сканер и сокет и весь клиент будут закрыты.
            scanner.close();
            //=======================================================


            //Закрываем сокет.
            client.close();


        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
