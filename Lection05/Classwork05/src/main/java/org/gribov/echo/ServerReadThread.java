package org.gribov.echo;

import java.io.*;
import java.net.Socket;

/**
 * Создадим новый класс ServerReadThread.java тут будет лежать описание работы сервера с входящим
 * потоком. Так как этот класс должен стать потоком, он должен расширять класс Thread
 * а значит к сигнатуре класса нам нужно добавить extends Thread.
 */

public class ServerReadThread extends Thread {
    Socket socket;
    public ServerReadThread(Socket socket) {
        this.socket = socket;
        this.run();
    }

    /**
     * Метод run(), в него я положил всё, что касается потоков ввода и вывода
     */
    @Override
    public void run() {
        try {

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            //Создаём новою строку, для хранения входящего сообщения.
            String inLine;

            OutputStream outStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outStream);

            //Цикл крутится, пока работает поток,
            //Если пришедшее сообщение не null, а значит что-то пришло со стороны клиента.
            while ((inLine = reader.readLine()) != null){
                //В самом теле передаём методом println класса PrintStream сообщение обратно клиенту.
                //Формат сообщения «(№ Порта клиента) + inLine».
                printStream.println("("+socket.getPort()+") "+ inLine);
                /*
                Класс сокет предоставляет два метода, getLockalPort() и getPort().
                Первый - возвращает номер порта который слушает сервер.
                Второй - номер порта по которому сервер подключился к клиенту.
                Таким образом можно легко идентифицировать клиентов!
                */
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
