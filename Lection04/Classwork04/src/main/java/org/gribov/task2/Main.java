package org.gribov.task2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    public static void main(String[] args) {

        /* Configures settings from hibernate.cfg.xml
        В первой строке мы объявляем и инициализируем экземпляр класса StandardServiceRegistry.
        Этот класс содержит механизмы связи с сервером базы данных и менеджер передачи запросов.
        Файл конфигурации Hibernate, который мы ранее создали, используется этим классом.
        Поскольку мы разместили файл в нужном каталоге с правильным именем, конфигурировать
        его можно без дополнительных ссылок.
        */
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

        /*
        Далее идет создание SessionFactory. Согласно документации, это "неизменяемый,
        пожаробезопасный объект с компилированным маппингом для одной базы данных".
        Его нужно инициализировать всего один раз.
        Экземпляр SessionFactory используется для получения объектов Session, которые используются для операций
        с базами данных.
         */
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        /*
        После получения SessionFactory мы создаем объект Session. Этот объект связывает наше приложение с Hibernate.
         */
        Session session = sessionFactory.openSession();

        /*
        Затем создается объект magic, экземпляр класса Magic с использованием параметризованного конструктора.
         */
        Magic magic = new Magic("Волшебная стрела", 10,0,0);

        /*
        Далее идет работа с сессией.
        Важно отметить, что сохранять сущности можно только в рамках транзакций. Поэтому мы начинаем транзакцию
         */
        session.beginTransaction();

        //затем сохраняем объект в базе данных
        session.save(magic); //старый метод был - "save"

        //закрываем транзакцию с записью данных
        session.getTransaction().commit();

        //После этого сессия закрывается.
        session.close();

    }
}
