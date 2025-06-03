package org.gribov.task3;

/**todo
 *  * Выполним рефакторинг нашего приложения
 */

/**
 * Мы разобрали полноценное CRUD – приложение.
 * ● C -> Create/Insert
 * ● R -> Retrieve
 * ● U -> Update
 * ● D -> Delete
 * Фактически, данное приложение способно создавать, выбирать, обновлять и
 * удалять данные из базы данных.
 */
public class Main {
    public static void main(String[] args) {
        //Db.createDB(); //заполнить БД
        Db.readDB(); //прочитать БД
        //Db.updateBD(); //изменить БД
        //Db.deleteBD(); //удалить из БД
    }
}
