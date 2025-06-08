package org.gribov.chat.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.Scanner;

public class Program {

    /**
     * Точка входа клиента
     */
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите своё имя: ");
            // Укажем свое имя - основной идентификатор для подключения к серверу.
            String name = scanner.nextLine();
            // Установка соединения с сервером (ip адрес сервера, порт который слушает сервер).
            Socket socket = new Socket("localhost", 1400);
            // Создание "обертки" для клиента.
            Client client = new Client(socket, name);
            InetAddress inetAddress = socket.getInetAddress();
            System.out.println("InetAddress: " + inetAddress);
            String remoteIp = inetAddress.getHostAddress();
            System.out.println("Remote IP: " + remoteIp);
            // При подключении, сервер назначит рабочий порт из разрешённого пула портов (это будет не 1400 порт).
            // Каждый клиент будет отжирать один свободный порт.
            System.out.println("LocalPort: " + socket.getLocalPort());
            System.out.println("Если Вы желаете написать личное сообщение участнику беседы, " +
                    "введите @имя и далее через пробел с большой буквы нужный текст. ");
            System.out.println("Пример: @Сергей Совершенно секретный текст!\n");

            // Инициализация потока на чтение данных.
            client.listenForMessage();

            // Инициализация процедуры, позволяющей осуществлять запись данных в поток сокета.
            client.sendMessage();
        }
        catch (UnknownHostException e) {
            //Неизвестный хост.
            e.printStackTrace();
        } catch (IOException e) {
            //Исключение возникает в момент создания объекта сокета.
            e.printStackTrace();
        }
    }

}
