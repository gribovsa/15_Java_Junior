package org.gribov.task2;





/**
 * todo
 *          Hibernate
 *      Если речь идет о более сложных структурах с гораздо более сложными
 *  запросами, было бы удобно переложить проблемы курсоров и открытых соединений
 *  в чистых JDBC  на плечи вспомогательной библиотеки.
 *      Такой подход существует и называется ORM, что расшифровывается как
 *  Object-Relational Mapping (Отображение объектов на реляционные базы данных).
 *  Суть ORM заключается в обращении к реляционным базам данных как к объектам.
 *  Реализация этого подхода осуществляется при помощи вспомогательных
 *  библиотек. Одной из таких популярных библиотек является Hibernate.
 */


import javax.persistence.*;

/**
 *      Пока что этот класс пуст, но уже можно выделить несколько интересных и важных
 *  моментов для ORM.
 *      Во-первых, в классе появилась аннотация @Entity. Эта
 *  аннотация указывает, что мы определяем объект-сущность. Другими словами,
 *  объект, созданный на основе этого класса, будет использоваться Hibernate при
 *  взаимодействии с базой данных. Объекты-сущности должны следовать
 *  спецификации POJO, что означает, что поля должны быть приватными, иметь
 *  геттеры и сеттеры, а также класс должен иметь пустой конструктор. Обычно также
 *  переопределяют метод toString для удобства.
 *      Вторым важным моментом является аннотация @Table. С параметром name мы
 *  связываем класс с определенной схемой и таблицей. Таким образом, мы создали
 *  класс-сущность и привязали его к таблице. Давайте добавим необходимые поля.
 */

@Entity
@Table(name = "test.magic")

/**
 *  Мы указали класс для маппинга в конфигурации (см resources/hibernate.cfg.xml),
 *  но самого класса у нас пока нет.
 *  Давайте создадим его. Сначала создадим пакет task2, а затем внутри него
 *  POJO-класс Magic.
 */
public class Magic {

    /**
     * Первая аннотация @Id является обязательной и определяет первичный ключ.
     * Аннотация @GeneratedValue определяет стратегию формирования
     * ключа. В данном примере используется GenerationType.IDENTITY, самый простой
     * способ конфигурации ключа, который опирается на auto-increment колонку в
     * таблице.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idmagic;

    /**
     * Аннотация @Column привязывает поле к колонке в базе данных. Если
     * название поля совпадает с названием колонки, привязывать не обязательно.
     */
    @Column(name = "название")
    private String name;
    @Column(name = "повреждение")
    private int damage;
    @Column(name = "атака")
    private int attBonus;


    /**
     * Конструктор с ключами
     * @param name имя
     * @param damage повреждение
     * @param attBonus атака
     */
    public Magic(String name, int damage, int attBonus) {
        this.name = name;
        this.damage = damage;
        this.attBonus = attBonus;
    }

    /**
     * Конструктор пустой
     */
    public Magic() {
    }


    /**
     * Геттеры
     */
    public int getIdMagic() {
        return idmagic;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getAttBonus() {
        return attBonus;
    }


    /**
     * Сеттеры
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setAttBonus(int attBonus) {
        this.attBonus = attBonus;
    }
}
