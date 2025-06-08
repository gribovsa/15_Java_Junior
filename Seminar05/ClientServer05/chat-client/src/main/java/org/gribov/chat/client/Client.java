package org.gribov.chat.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Класс клиент
 */
public class Client {

    private final Socket socket;    // Сокет - дескриптор для связи между клиентом и сервером по протоколу IP.
    private final String name;      // Имя клиента.

    /**
     * BufferedWriter — это класс из библиотеки Java, который используется для записи данных в поток байтов.
     * Он работает как буфер между приложением и физическим устройством, улучшая производительность системы.
     */
    private BufferedWriter bufferedWriter;

    /**
     * BufferedReader — это класс из библиотеки Java, предназначенный для чтения данных из потока байтов.
     */
    private BufferedReader bufferedReader;

    /**
     * Конструктор, создающий нового клиента, который подключается к серверу через указанный сокет (socket).
     * При этом создается поток чтения и поток записи для обмена данными между клиентом и сервером.
     * Если при создании потоков возникает ошибка, то закрываются все открытые ресурсы (closeEverything).
     *
     * @param socket   — дескриптор для связи между клиентом и сервером по протоколу IP.
     * @param userName — имя клиента.
     */
    public Client(Socket socket, String userName) {
        this.socket = socket;
        name = userName;
        try {
            // socket.getOutputStream() - возвращает выходной поток байт
            // socket.getInputStream() - возвращает входной поток байт
            // bufferedWriter bufferedReader - имеют перегруженные методы записи, чтения строк
            // это удобно, так как мы работаем со строками, а не с байтами
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            //В случае исключений закрываем потоки и сокет
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }


    /**
     * Слушатель для входящих сообщений
     */
    public void listenForMessage() {
        //Слушать мы будем в отдельном потоке, чтобы отправка не помешала слушать.
        new Thread(new Runnable() {
            @Override
            public void run() {
                String message;
                //Слушать будем, пока есть соединение с сервером
                while (socket.isConnected()) {
                    try {
                        //Слушаем/считываем данные и печатаем в консоль
                        message = bufferedReader.readLine();
                        System.out.println(message);
                    } catch (IOException e) {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start(); //запустили этот отдельный поток на исполнение (исполнение, пока работает метод run)
    }

    /**
     * Отправить сообщение
     */
    public void sendMessage() {
        try {
            // Прежде всего отправка своего имени на сервер.
            bufferedWriter.write(name);
            // Отправка символа перехода на следующую строку.
            bufferedWriter.newLine();
            // Вызов метода немедленной отправки сообщения.
            bufferedWriter.flush();

            // Создание объекта сканер.
            Scanner scanner = new Scanner(System.in);

            // Цикл ожидания и немедленной отправки сообщения,
            while (socket.isConnected()) {
                //Как только в консоли появилось сообщение
                String message = scanner.nextLine();

                //================================================================================
                //Сообщения адресованные кому-то (персональные сообщения).
                //Если сообщение начинается с символа @
                if (message.startsWith("@")) {
                    //найдём в тексте сообщения пробел
                    int i = message.indexOf(' ');
                    //с начала сообщения до пробела - будет имя получателя
                    String recipient = message.substring(0, i);
                    //всё что после пробела - тело сообщения
                    String text = message.substring(i);
                    //сформируем и отправим персональное сообщение (форма будет отличной от остальных сообщений).
                    bufferedWriter.write(recipient + " " + name + ": " + text);
                    //=============================================================================

                } else {
                    //Отправляем все остальные не персональные сообщения серверу.
                    bufferedWriter.write(name + ": " + message);
                }
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }


    /**
     * Метод closeEverything закрывает входящий и исходящий потоки, если переменная не равна нулю и вызывала исключение.
     * После этого закрывается socket.
     *
     * @param socket         — дескриптор для связи между клиентом и сервером по протоколу IP.
     * @param bufferedReader — это класс из библиотеки Java, предназначенный для чтения данных из потока байтов.
     * @param bufferedWriter — это класс из библиотеки Java, который используется для записи данных в поток байтов.
     */
    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            //Если были корректно проинициализированы переменные.
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
