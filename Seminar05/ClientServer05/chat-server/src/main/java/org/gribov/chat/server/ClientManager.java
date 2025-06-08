package org.gribov.chat.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Менеджер работы с клиентом.
 * У каждого клиента подключённого к серверу есть свой клиент-менеджер!
 * Сюда мы передаём клиентский сокет, и здесь мы храним список клиентских сокетов.
 * Класс имплементирует интерфейс Runnable, так как экземпляры класса будут запускаться в разных потоках.
 * (кстати на клиенте показан другой способ - с анонимным классом)
 */
public class ClientManager implements Runnable {

    private final Socket socket;            // Дескриптор для связи между клиентом и сервером по протоколу IP.
    private BufferedReader bufferedReader;  // Класс библиотеки Java, предназначенный для чтения данных из потока байтов.
    private BufferedWriter bufferedWriter;  // Класс библиотеки Java, используемый для записи данных в поток байтов.
    private String name;                    // Имя клиента.

    // Коллекция клиент-менеджеров, т.е всех клиентов подключённых к серверу.
    public final static ArrayList<ClientManager> clients = new ArrayList<>();


    /**
     * Конструктор
     */
    public ClientManager(Socket socket) {
        this.socket = socket;
        try {
            // socket.getOutputStream() - возвращает выходной поток байт
            // socket.getInputStream() - возвращает входной поток байт
            // bufferedWriter bufferedReader - имеют перегруженные методы записи, чтения строк
            // это удобно, так как мы работаем со строками, а не с байтами
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Самое первое сообщение, которое отправит клиент при установке соединения - это имя (мы так договорились)
            name = bufferedReader.readLine(); //получу это имя.
            //Если я успешно считал это имя, то добавляю экземпляр этого клиент-менеджера в список clients.
            clients.add(this);
            System.out.println(name + " подключился к чату.");
            broadcastMessage("Server: " + name + " подключился к чату.");
        } catch (IOException e) {
            //в противном случае, если работа с клиентом невозможна.
            closeEverything(socket, bufferedReader, bufferedWriter);
        }


    }

    /**
     * Класс имплементирует интерфейс Runnable, необходимо реализовать метод run().
     */
    @Override
    public void run() {
        String massageFromClient;

        while (socket.isConnected()) {
            try {
                //Читаю сообщение от клиента.
                massageFromClient = bufferedReader.readLine();
                /*if (massageFromClient == null){
                    // для  macOS
                    closeEverything(socket, bufferedReader, bufferedWriter);
                    break;
                }*/
                //Обрабатываю сообщение.
                broadcastMessage(massageFromClient);
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break; //выход из цикла
            }
        }
    }

    /**
     * Метод broadcastMessage реализует алгоритм действий по отправке сообщений клиентам.
     * Происходит перебор клиентов из списка и в случае, если имя клиента не соответствует имени, с которого поступило
     * сообщение, то ему пересылается это сообщение.
     * При возникновении ошибок, вызывается метод closeEverything.
     *
     * @param message - текст сообщения от клиента.
     */

    private void broadcastMessage(String message){

        //Проходим по всем клиентам
        for (ClientManager client: clients) {
            try {
                //=================================================================
                //Обработка персональных сообщений
                //Если начальный символ сообщения @
                if (message.startsWith("@")) {
                    //Берём строку и превращаем её в массив
                    String[] parts = message.split("\\s+", 2);
                    String recipient = null;
                    String privateMessage = null;
                    //если длина массива 2 и первый элемент начинается с @
                    if (parts.length == 2 && parts[0].startsWith("@")){
                        //первый элемент будет адресат
                        recipient = parts[0].substring(1);
                        //второй элемент персональное сообщение
                        privateMessage = parts[1];
                    }
                    //если текущее имя клеента соответствует адресату
                    if (client.name.equals(recipient)) {
                        //тогда отправляем ему персональное сообщение.
                        client.bufferedWriter.write(privateMessage);
                        client.bufferedWriter.newLine();
                        client.bufferedWriter.flush();
                    }
                    //==============================================================
                } else {
                    //Здесь отправляем неперсональные сообщения всем клиентам кроме себя (т.е ретранслируем)
                    if (!client.name.equals(name)) {
                        client.bufferedWriter.write(message);
                        client.bufferedWriter.newLine();
                        client.bufferedWriter.flush();
                    }
                }
            }
            catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }


    /**
     * Метод closeEverything обеспечивает удаление клиента-менеджера из коллекции, закрывает буферы на чтение и на запись,
     * а также клиентский сокет, в случае возникновения ошибки.
     *
     * @param socket         — дескриптор для связи между клиентом и сервером по протоколу IP.
     * @param bufferedReader — это класс из библиотеки Java, предназначенный для чтения данных из потока байтов.
     * @param bufferedWriter — это класс из библиотеки Java, который используется для записи данных в поток байтов.
     */
    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        // Удаление клиента из коллекции
        removeClient();
        try {
            // Завершаем работу буфера на чтение данных
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            // Завершаем работу буфера для записи данных
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            // Закрытие соединения с клиентским сокетом
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод removeClient выполняет удаление клиент-менеджера из коллекции при возникновении ошибки.
     */
    private void removeClient() {
        clients.remove(this);
        System.out.println(name + " покинул чат.");
        broadcastMessage("Server: " + name + " покинул чат.");
    }

}
