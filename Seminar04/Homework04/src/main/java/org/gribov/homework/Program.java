package org.gribov.homework;

import org.gribov.models.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * todo
 *  Задание
 *  =======
 *  Создайте базу данных (например SchoolDB).
 *  В этой базе данных создайте таблицу Courses с полями id (ключ), title, duration.
 *  Настройте Hibernate для работы с Вашей базой данных.
 *  Создайте Java - класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
 *  Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
 *  Убедитесь, что каждая операция выполняется в отдельной транзакции.
 */


public class Program {

    public static void main(String[] args) {
        //Создать/добавить объект в БД
        Db.createInsertDB();

        //Извлечь объект из БД
        Db.retrieveDB();

        //Внести изменение в объект из БД
        Db.updateBD();

        //Удаление объекта из БД
        //Db.deleteBD();
    }
}
