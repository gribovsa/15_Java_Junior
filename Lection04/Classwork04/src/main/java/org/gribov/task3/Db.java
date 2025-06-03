package org.gribov.task3;

import org.gribov.task2.Magic;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * todo
 * Теперь класс Db - класс работы с БД
 */

public class Db {

    public static void createDB() {
        Connector connector = new Connector();
        Session session = connector.getSession();
        Magic magic = new Magic("Волшебная стрела", 10, 0, 0);


        session.beginTransaction();
        session.save(magic);

        magic = new Magic("Молния", 25, 0, 0);
        session.save(magic);

        magic = new Magic("Каменная кожа", 0, 0, 6);
        session.save(magic);

        magic = new Magic("Жажда крови", 0, 6, 0);
        session.save(magic);

        magic = new Magic("Жажда крови", 0, 6, 0);
        session.save(magic);

        magic = new Magic("Проклятие", 0, -3, 0);
        session.save(magic);

        magic = new Magic("Лечение", -30, 0, 0);
        session.save(magic);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * Теперь давайте попробуем загрузить объекты из базы данных.
     * В этом коде, прежде всего, следует отметить, что я обернул создание сессии в блок
     * try/catch с использованием автоматического закрытия ресурсов.
     * Создавшаяся сессия будет обязательно закрыта.
     */
    public static void readDB() {
        Connector connector = new Connector();
        try (Session session = connector.getSession()) {
            /*
            Выборка осуществляется с использованием метода createQuery().
            Первый параметр - это запрос в формате HQL, который буквально
            указывает, из какой таблицы читать, а второй - это класс-сущность, описывающий
            данные в таблице.
            Для получения списка результатов используется метод getResultList().
             */
            List<Magic> books = session.createQuery("FROM Magic", Magic.class).getResultList();
            books.forEach(b -> {
                System.out.println("Book of Magic : " + b);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Давайте попробуем теперь изменить один из объектов.
     */

    public static void updateBD(){
        Connector connector = new Connector();
        try (Session session = connector.getSession()) {
            String hql = "from Magic where id = :id"; //Сначала я задал строку hql.
            Query<Magic> query = session.createQuery( hql, Magic.class); //Далее создаю запрос (Query)
            query.setParameter("id", 4);
            Magic magic = query.getSingleResult(); //Метод getSingleResult() получаю объект с id 4
            System.out.println(magic);
            //Дальше изменяю  его параметры используя сеттеры.
            magic.setAttBonus(12);
            magic.setName("Ярость");
            session.beginTransaction(); //Начинаю транзакцию
            session.update(magic); //Изменяю объект
            session.getTransaction().commit(); //Закрываю транзакцию
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Мы умеем обновлять элементы в базе данных!
     * Осталось научиться удалять их оттуда!
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
            Transaction t = session.beginTransaction();
            //получаем лист наших объектов
            List<Magic> books = session.createQuery("FROM Magic", Magic.class).getResultList();
            //проходим по листу и удаляем каждый объект
            books.forEach(b -> {
                session.delete(b);
            });
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
