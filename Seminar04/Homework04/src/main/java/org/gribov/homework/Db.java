package org.gribov.homework;

import org.gribov.models.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Random;

/**
 * Класс для работы с БД
 * CRUD – приложение.
 * ● C -> Create/Insert
 * ● R -> Retrieve
 * ● U -> Update
 * ● D -> Delete
 */
public class Db {

    private static final String[] titles = new String[]{"Математика", "Физика",
            "Химия", "Биология", "Литература", "Информатика", "Физкультура"};
    private static final Random random = new Random();


    /**
     * Метод Create/Insert - создать/добавить объект в БД
     */
    public static void createInsertDB() {

        Connector connector = new Connector();


        for (int i = 0; i < titles.length; i++) {
            //создание сессии в блоке try with resources
            //использую автоматического закрытия сессии
            try (Session session = connector.getSession()) {
                // Создание объекта курс
                Course course = new Course(titles[i], random.nextInt(350, 651));
                // Начинаю транзакцию
                session.beginTransaction();
                session.persist(course); //раньше было save
                System.out.println("Объект: " +  course + " добавлен в БД успешно");
                // Завершаю транзакцию
                session.getTransaction().commit();
            } catch (Exception e){
                e.printStackTrace();
            };
        }

    }

    /**
     * Метод Retrieve - извлечь объект из БД
     */
    public static void retrieveDB() {
        Connector connector = new Connector();
        try (Session session = connector.getSession()) {
            /*
            Чтение объекта из базы данных
            Выборка осуществляется с использованием метода createQuery().
            Первый параметр - это запрос в формате HQL, который буквально
            указывает, из какой таблицы читать, а второй - это класс-сущность, описывающий
            данные в таблице.
            Для получения списка результатов используется метод getResultList().
             */
            List<Course> coursesList = session.createQuery("FROM Course", Course.class).getResultList();
            //читаем объекты и складываем в коллекцию List
            for (Course c : coursesList) {
                System.out.println("Курс в школьной программе: " + c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод Update - внести изменение в объект из БД
     * Все объекты - курсы с названием Физика, будут иметь продолжительность 1000 часов
     */
    public static void updateBD(){
        Connector connector = new Connector();
        try (Session session = connector.getSession()) {
            //Далее создаю запрос (Query), цель найти название Физика
            Query<Course> query = session.createQuery( "FROM Course WHERE title =: title", Course.class);
            //Передаю параметры запроса - Физика
            query.setParameter("title", "Физика");
            //Получаю все объекты - курсы с названием Физика, сохраняю их в лист
            List <Course> courseUpdateList = query.getResultList();
            //Перебираю полученные объекты
            for (Course courseUpdate : courseUpdateList) {
                System.out.println("Объект: " + courseUpdate + " будет изменён");
                //Дальше изменяю параметр "продолжительность" у объекта - курса
                courseUpdate.setDuration(1000);
                //Начинаю транзакцию
                session.beginTransaction();
                //Изменяю объект
                session.merge(courseUpdate); //раньше было update

                //Закрываю транзакцию
                session.getTransaction().commit();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод Delete - удаление объекта из БД
     * Просто удалю все из таблицы
     */

    public static void deleteBD(){
        Connector connector = new Connector();
        /*
        Удаление проходит в рамках транзакции, для этого я создаю её первым делом.
        Сам метод удаления delete(b) в параметре требует только ссылку на объект,
        данные которого мы хотим удалить из базы. Ну и закрытие транзакции,
        которое я вызываю после удаления всех полученных из бузы объектов.
         */
        try (Session session = connector.getSession()) {
            //Начинаю транзакцию
            session.beginTransaction();
            //получаем лист наших объектов
            List<Course> coursesList = session.createQuery("FROM Course", Course.class).getResultList();
            //проходим по листу и удаляем каждый объект
            for (Course b : coursesList) {
                session.remove(b); //раньше было delete

            }
            //Закрываю транзакцию
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
