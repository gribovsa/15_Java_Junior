package org.gribov.task1;

import java.sql.*;
import java.util.ArrayList;

/**
 * todo
 *      Давайте немного организуем код, выделив создание соединения в отдельный метод.
 */

public class Test2 {
    private static final String url = "jdbc:mysql://localhost:3306";
    private static final String user = "gribov";
    private static final String password = "1234";


    public static void main(String[] args) throws SQLException {
        Connection connection = getConnection(url, user, password);
        Statement statement = connection.createStatement();

        //заполняем БД
        //statement.execute("DROP SCHEMA `test` ;");
        statement.execute("CREATE SCHEMA `test` ;");
        statement.execute("CREATE TABLE `test`.`table` (\n" +
                " `id` INT NOT NULL,\n" +
                " `firstname` VARCHAR(45) NULL,\n" +
                " `lastname` VARCHAR(45) NULL,\n" +
                " PRIMARY KEY (`id`));");

        statement.execute("INSERT INTO `test`.`table` (`id`,`firstname`,`lastname`)\n" +
                "VALUES (1,'Иванов','Иван');");
        statement.execute("INSERT INTO `test`.`table` (`id`,`firstname`,`lastname`)\n" +
                "VALUES (2,'Петров','Пётр');");


        //читаем БД
        System.out.println(getData(statement));

    }


    /**
     * Метод создания соединения
     * Теперь для получения нового подключения мы можем просто вызвать этот метод. И
     * тут возникает первая проблема. Она в том, что connection это ресурс сервера баз
     * данных, и этот ресурс может закончиться. Например, вы забыли закрыть ресурс, а
     * приложение продолжает работать. Серверу придётся держать связь открытой.
     *
     * @param url
     * @param user
     * @param password
     * @return
     */
    public static Connection getConnection(String url, String user, String password) {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }


    /**
     * Метод выборки данных
     * Здесь, на первый взгляд, все в порядке. Но предположим, что мы извлекаем не все
     * данные, а только те, которые соответствуют определенному условию. То есть в
     * ResultSet остается какая-то часть данных. Это приведет к тому, что курсор, который
     * является основой ResultSet с одной стороны и ресурсом сервера с другой, останется
     * в памяти. Но курсоры тоже не бесконечны! Проблемы с курсорами и соединениями
     * усложняют работу чистых JDBC-приложений с таблицами.
     *
     * @param statement
     * @return
     * @throws SQLException
     */
    public static ArrayList<String> getData(Statement statement) throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        ResultSet set = statement.executeQuery("SELECT * FROM `test`.`table`;");
        while (set.next()) {
            list.add(set.getString(3) + " " + set.getString(2) + " " + set.getInt(1));
        }
        return list;
    }


}
