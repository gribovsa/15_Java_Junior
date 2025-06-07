package org.gribov;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс сервер
 */
public class Server {
    //Понадобится нам сервер сокет, добавим его сразу
    private final ServerSocket serverSocket;

    //Понадобится параметризированный конструктор, принимающий сокет
    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     * Метод запускающий наш сервер
     */
    public void runServer() {
        try {
            while (!serverSocket.isClosed()) {
                //ServerSocket.accept() ждет/блокирует, пока клиент подключится,
                // установит соединение и вернет вам объект на стороне сервера
                Socket socket = serverSocket.accept();
                System.out.println("Подключен новый клиент!");
                //создаём объект клиента на основе того самого класса ClientManager,
                //возьмёт на себя весь функционал связанный с межклиентным общением
                ClientManager client = new ClientManager(socket);
                //и будет выполняться как отдельный поток.
                Thread thread = new Thread(client);
                thread.start();
                /*
                Многопоточность, это необходимая составляющая, позволяющая серверу
                слушать всех клиентов и рассылать всем клиентам сообщения.
                 */
            }
        } catch (IOException e) {
            //Попасть сюда мы можем, только если возникнет проблема со связью,
            //обработка исключения может свестись просто к закрытию сокета.
            closeSocket();
        }
    }

    /**
     * Метод закрывающий наш сокет
     */
    public void closeSocket() {
        try {
            //Закрыть сокет мы можем только если он не нулевой
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Точка входа в сервер
     */
    public static void main(String[] args) throws IOException {
        //Первой строкой объявляем и инициализируем сервер сокет.
        ServerSocket serverSocket = new ServerSocket(1300);
        //Затем создаём объект, экземпляр написанного нами только что класса и
        //передаём ему в параметр конструктора уже готовый сокет.
        Server server = new Server(serverSocket);
        //Ну и запускаем сервер!
        server.runServer();
        /*
        Обработка исключений здесь тоже требуется, но, так как это точка входа, и выше
        нашего кода нет, мы просто пробросим исключение добавив в сигнатуру метода
        throws IOEception.
         */
    }
}

