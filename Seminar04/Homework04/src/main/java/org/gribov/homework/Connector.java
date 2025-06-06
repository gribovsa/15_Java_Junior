package org.gribov.homework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Создадим класс Connector и перенесём в него всё, что касается создания sessionFactory
 * а выделение сессии вынесем в метод getSession()
 */
public class Connector {
    final StandardServiceRegistry registry;
    SessionFactory sessionFactory;


    public Connector() {
        registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }


    /**
     * Метод выделения сессии
     * @return возвращает сессию session
     */
    public Session getSession() {
        return sessionFactory.openSession();
    }
}

