package org.gribov;


import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Класс ClientManager, возьмёт на себя весь функционал связанный с межклиентным
 * общением.
 * Класс ClientManager работает на стороне сервера.
 * Наш новый класс реализует интерфейс Runnable. Это обязательно так как класс,
 * выполняющийся в потоке, должен реализовать метод run объявленный в этом
 * интерфейсе.
 */
public class ClientManager implements Runnable {


    //Нам нужен наш канал связи, для этого объявим локальное приватное поле класса Socket.
    private Socket socket;
    //BufferedReader и BufferedWriter для того, чтобы передавать и принимать данные.
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    //Имя клиента
    private String name;
    //Публичный список клиентов, и содержит он экземпляра класса ClientManager
    public static ArrayList<ClientManager> clients = new ArrayList<>();
    /*
    Модификатор static, привязывающий это поле не к экземпляру класса, а к самому классу.
    Фактически, откуда бы мы не работали с этим списком, мы будем обращаться к одному общему списку.
     */


    /**
     * Конструктор
     * В теле конструктора мы будем работать с сокетами и потоками.
     * @param socket сокет
     */
    public ClientManager(Socket socket) {
        try {
            //Инициализируем наш локальный сокет из пришедшего в параметрах
            this.socket = socket;
            /*
            Далее две строки инициализируют buffered Writer и Reader.
            В яве классы описывающие потоки бывают двух видов:
            Названия первых заканчиваются на Stream и они работают с байтами,
            названия вторых заканчиваются на Writer или Reader и они работают с символами.
            Существуют ещё и обёртки.
            Для OutputStreamWriter есть обёртка BufferWriter.
            Он позволяет буферизировать поток и упростить работу с ним.
             */
            bufferedWriter = new BufferedWriter(new
                    OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new
                    InputStreamReader(socket.getInputStream()));
            //Мы ждём пока из потока не прейдет строка текста, т.е
            //мы ждём пока клиент введёт своё имя и сохраним его в переменную name.
            name = bufferedReader.readLine();
            //Дальше мы добавляем нового клиента в список.
            clients.add(this);
            //А затем вызываем метод рассылки.
            broadcastMessage("Server: "+name+" подключился к чату.");
        } catch (IOException e) {
            /*
            Если произошло исключение, значит какая-то проблема.
            Но не со всеми клиентами или сервером, а только с этим клиентом.
            Поэтому в теле catch пока поставим вызов метода закрывающего все ресурсы, а
            чуть позже этот метод напишем.
             */
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /**
     * Добавим реализацию метода run.
     */
    @Override
    public void run() {
        //Первое, что понадобится в этом методе, это локальная строка для передачи в чат.
        String massageFromClient;
        //Напишем цикл, который будет крутиться, пока клиент подключен
        while (socket.isConnected()){
            try {
                //Если всё нормально ждём сообщение от клиента и,
                //дождавшись, рассылаем всем остальным клиентам.
                massageFromClient = bufferedReader.readLine();
                broadcastMessage(massageFromClient);
            } catch (IOException e){
                //Если возникло исключение,
                //закрываем все ресурсы клиента и останавливаем поток.
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    /**
     * Метод рассылки сообщений
     * @param massageToSend принимает строку для передачи
     */
    private void broadcastMessage(String massageToSend) {
        //Мы перебираем всех клиентов из списка
        for (ClientManager client: clients) {
            try {
                //Если имя клиента не такое же, как у нас
                if (!client.name.equals(name)) {
                    //используем bufferWriter клиента для передачи ему строки
                    //метод write() записывает строку
                    client.bufferedWriter.write(massageToSend);
                    //метод newline() передаёт символ переноса строки
                    client.bufferedWriter.newLine();
                    //метод flush() чистит буфер дабы он не переполнялся и не рос
                    client.bufferedWriter.flush();
                }
            } catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    /**
     * Метод закрывает все ресурсы
     * Задача этого метода, просто закрыть все ресурсы и удалить клиента из списка.
     * @param socket
     * @param bufferedReader
     * @param bufferedWriter
     */
    private void closeEverything(Socket socket, BufferedReader bufferedReader,
                                 BufferedWriter bufferedWriter) {

        //Первым делом удаляем текущего клиента, removeClient()
        removeClient();
        try {
            //Потом мы просто проверяем что ресурсы инициализированы, то
            //есть не null и, если это так, закрываем их.
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Метод удаления клиента
     */
    public void removeClient(){
        //Удаляем клиента из списка.
        clients.remove(this);
        //Сообщаем всем остальным, что клиент покинул чат.
        broadcastMessage("SERVER: "+name+" покинул чат.");
    }
}

