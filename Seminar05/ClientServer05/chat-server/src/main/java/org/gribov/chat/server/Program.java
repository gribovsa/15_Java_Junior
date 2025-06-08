package org.gribov.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.UnknownHostException;

public class Program {

    /**
     * Точка входа сервера
     */
    public static void main(String[] args) {
        try
        {
            //Объект ServerSocket, слушает входящее подключение на порту 1400
            //в случае запроса от клиента назначит свободный порт из допустимого пула портов
            ServerSocket serverSocket = new ServerSocket(1400);
            Server server = new Server(serverSocket);
            //Вызов метода запуска сервера
            server.runServer();
        }
        catch (UnknownHostException e){
            e.printStackTrace();
        }
        catch (IOException e){
            //Если порт занят
            e.printStackTrace();
        }
    }

}
