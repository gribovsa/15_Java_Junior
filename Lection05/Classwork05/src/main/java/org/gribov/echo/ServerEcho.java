package org.gribov.echo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Создадим полноценный эхо сервер! Отличаться он будет тем, что не станет закрываться сразу,
 * а продолжит отвечать на запросы.
 * Класс ServerEcho, работает с ServerReadThread и ClientEcho.
 */
public class ServerEcho {
    public static void main(String[] args) {
        try{
            //Создаём серверный сокет
            ServerSocket serverSocket = new ServerSocket(1300);
            //ждём подключение
            Socket socket = serverSocket.accept();
            //создаём само запускающийся поток
            ServerReadThread thread = new ServerReadThread(socket);
            //Просто крутимся в while, isAlive вернёт ложь только если поток будет закрыт.
            while (!thread.isAlive()){}
            socket.close();
            serverSocket.close();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
