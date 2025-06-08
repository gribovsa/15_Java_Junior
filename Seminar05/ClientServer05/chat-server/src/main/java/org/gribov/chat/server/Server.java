package org.gribov.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс сервер
 */
public class Server {


    private final ServerSocket serverSocket;        //Инициализирую сокет

    /**
     * Конструктор
     * @param serverSocket получаю сокет
     */
    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     * Метод runServer запускает цикл мониторинга новых подключений в рамках основного потока приложения.
     * В случае появления исключений, вызывается метод закрытия серверного сокета.
     */
    public void runServer() {
        try {
            //Работает цикл while, если серверный сокет не закрыт
            while (!serverSocket.isClosed()) {
                //Главный метод на серверном сокете accept, переводит основной поток приложения в режим ожидания
                //подключения нового сокета.
                //Как только произошло подключение, принимаем информацию клиентского сокета и создаём объект сокета
                Socket socket = serverSocket.accept();
                //Создадим экземпляр класса ("обёртки"), куда мы передадим клиентский сокет, как только подключится
                // клиент и где бы будем хранить список клиентских сокетов.
                ClientManager clientManager = new ClientManager(socket);
                System.out.println("Подключен новый клиент!");
                //Работа клиент менеджера будет происходить в рамках отдельного потока.
                //Создаём отдельный поток,
                Thread thread = new Thread(clientManager); //передаём clientManager, который имплементирует Runnable
                thread.start(); //и запускаем его в работу.
            }
        } catch (IOException e) {
            //Если что-то пойдёт не так, то завершаем работу сокета
            closeSocket();
        }
    }

    /**
     * Метод завершения работы сокета
     */
    private void closeSocket() {
        try {
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
