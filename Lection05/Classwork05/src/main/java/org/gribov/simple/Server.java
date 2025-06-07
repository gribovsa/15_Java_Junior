package org.gribov.simple;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Серверный сокет описан классом ServerSocket и тоже имеет несколько
 * конструкторов, но я использовал самый важный и интересный из них. В нём указан
 * порт, который слушает сервер находящийся по ip-адресу локальной машины.
 * В следующеё строке мы вызываем метод accept() и фактически начинаем слушать порт.
 * Логика работы:
 * Сначала ждём связи, потом настраиваем канал вывода, отправляем сообщение и
 * связь закрываем.
 */

public class Server {
    public static void main(String[] args) {
        try {
            //указан порт, который слушает сервер находящийся по ip-адресу локальной машины
            ServerSocket serverSocket = new ServerSocket(1300);
            //вызываем метод accept() и фактически начинаем слушать порт
            Socket socket = serverSocket.accept();

            //будет ответ сервера
            OutputStream outStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outStream);
            printStream.println("Hello!");

            socket.close();

            //Закрытие серверного сокета. Нужно это если вы
            //получили от клиента всё, чего ожидали и в дальнейшем слушать порт не
            //собираетесь.
            serverSocket.close();



        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}