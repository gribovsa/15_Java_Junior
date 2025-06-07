package org.gribov;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Класс клиент.
 */
public class Client {

    private final Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    //Строка со своим именем
    private final String name;


    /**
     * Конструктор принимает инициализированный сокет, и своё имя.
     *
     * @param socket   инициализированный сокет
     * @param userName своё (клиента) имя
     */
    public Client(Socket socket, String userName) {
        //Сохраняем их в локальные переменные.
        this.socket = socket;
        name = userName;
        try {
            //Дальше инициализируем bufferWriter и bufferReader
            bufferedWriter = new BufferedWriter(new
                    OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new
                    InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            //Если выскочило исключение, закрываем все ресурсы
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /**
     * Метод передачи сообщения.
     */
    public void sendMessage() {
        try {
            //начала посылаем серверу своё имя
            bufferedWriter.write(name);
            //посылаем символ переноса строки
            bufferedWriter.newLine();
            //очищаем буфер передачи
            bufferedWriter.flush();
            //потом ини циализируем сканер для чтения текста из консоли
            Scanner scanner = new Scanner(System.in);
            //пока есть связь
            while (socket.isConnected()) {
                //читаем строку и передаём прочитанное серверу
                String message = scanner.nextLine();
                bufferedWriter.write(name + ": " + message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            //При работе с сервером может выскочить исключение,
            //в этом случае мы закроем все ресурсы
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }


    /**
     * Метод слушателя сообщений.
     * Клиент должен уметь слушать сообщения.
     */
    public void listenForMessage() {
        //Метод должен работать в параллельном потоке, чтобы исполняя свой функционал
        //не отвлекать приложение от передачи сообщений.
        //Создаём анонимный Thread, переопределяем в нём метод run и запускаем( start() ).
        new Thread(new Runnable() {
            @Override
            public void run() {
                String messageFromGroup;
                //В потоке мы просто крутимся пока есть коннект
                while (socket.isConnected()) {
                    try {
                        //и ждём сообщения со стороны сервера,
                        //если сообщение пришло, печатаем его в консоль.
                        messageFromGroup = bufferedReader.readLine();
                        System.out.println(messageFromGroup);
                    } catch (IOException e) {
                        //Если со связью, что-то произошло,
                        //отлавливаем исключение и закрываем все ресурсы клиента.
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }


    /**
     * Метод завершения работы ресурсов
     * @param socket сокет
     * @param bufferedReader ресурс
     * @param bufferedWriter ресурс
     */
    private void closeEverything(Socket socket, BufferedReader bufferedReader,
                                 BufferedWriter bufferedWriter) {
        try {
            //Если ресурс инициализирован, закрываем его
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
            //Если возникает исключение выводим его стек в консоль.
            e.printStackTrace();
        }
    }

    /**
     * Точка входа клиента
     */
    public static void main(String[] args) throws IOException {
        //Мы объявляем и инициализируем сканер,
        //просим ввести имя пользователя.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите своё имя: ");
        String name = scanner.nextLine();
        //Подключаемся к серверу по порту 1300
        Socket socket = new Socket("localhost", 1300);
        //Объявляем и инициализируем клиента
        //В параметры конструктора передаём сокет подключения и имя пользователя.
        Client client = new Client(socket, name);
        //Затем вызываем методы для ожидания и отправки сообщений.
        client.listenForMessage();
        client.sendMessage();
    }
}

