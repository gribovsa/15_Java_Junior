package org.gribov.task1;

import java.sql.*;

/**todo
 *  JDBC (Java Database Connectivity)
 *  — это программный интерфейс, предоставляющий набор классов и методов для
 *  взаимодействия с базами данных из языка программирования Java.
 */

public class Test1 {
    private static final String url = "jdbc:mysql://localhost:3306";
    private static final String user = "gribov";
    private static final String password = "1234";


    public static void main(String[] args) {
        /*
            DriverManager - это класс, содержащий
        набор методов для инициализации JDBC-драйвера сервера базы данных и, в
        конечном итоге, для подключения к нему. По сути, он является основой JDBC!
            Метод getConnection() позволяет установить соединение с СУБД, предоставив ей
        адрес, логин и пароль.
         */
        try (Connection con = DriverManager.getConnection(url, user, password)){
            Statement statement = con.createStatement();
            //мы добавили просто удаление схемы, (на случай если она уже создана)
            statement.execute("DROP SCHEMA `test` ;");
            //попробуем создать БД с использованием SQL-запроса прямо из нашего приложения
            //кстати, в MySQL база данных называется схемой, это всего лишь название
            statement.execute("CREATE SCHEMA `test` ;");
            //в схему нужно добавить таблицу и поля
            statement.execute("CREATE TABLE `test`.`table` (\n" +
                    " `id` INT NOT NULL,\n" +
                    " `firstname` VARCHAR(45) NULL,\n" +
                    " `lastname` VARCHAR(45) NULL,\n" +
                    " PRIMARY KEY (`id`));");
            //отлично, давайте попробуем заполнить эти поля из программы
            statement.execute("INSERT INTO `test`.`table` (`id`,`firstname`,`lastname`)\n" +
            "VALUES (1,'Иванов','Иван');");
            statement.execute("INSERT INTO `test`.`table` (`id`,`firstname`,`lastname`)\n" +
            "VALUES (2,'Петров','Пётр');");


            /*
                Стейтмент возвращает результат работы запроса, а ResultSet создаёт указатель на
            первую строку результата. Метод next() возвращает истину, только если ещё есть не
            обработанные строки. Геттеры позволяют получить доступ непосредственно к
            данным. Таким образом осуществляется доступ к данным и их перебор. Запускаем!
             */
            ResultSet set = statement.executeQuery("SELECT * FROM `test`.`table`;");
            while (set.next()){
                System.out.println(set.getString(3) + " " + set.getString(2) + " " + set.getInt(1));
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
