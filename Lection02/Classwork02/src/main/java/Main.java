
/*todo
        РЕФЛЕКСИЯ (Reflection API)
        java.lang.reflect
        Class это и есть основа рефлексии, именно через
    него мы и будем с ней работать! Вообще Class создаётся при загрузке любого
    динамического класса и содержит информацию о его структуре! То есть как раз то
    что нам нужно!
 */


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException,
            InvocationTargetException, InstantiationException, IllegalAccessException {

        //Создали экземпляр класса, знаем что есть поля класса, а если не знаем...
        Car car0 = new Car("Волга");
        System.out.println(car0.name);


        //Можем найти конструктор методами рефлексии
        Class<?> car = Class.forName("Car");


        //метод getConstructors() возвращает список всех публичных конструкторов объявленных в классе
        Constructor<?>[] constructor = car.getConstructors();
        System.out.println(Arrays.toString(constructor)); //вернёт [public Car(java.lang.String)]


        //метод getDeclaredConstructors(), он возвращает и приватные конструкторы
        //Constructor<?>constructor1 = car.getDeclaredConstructor();


        //Метод newInstance() возвращает инициализированный объект указанного конструктора, а
        //нужные для конструктора параметры передаются в параметрах ему.
        Object gaz = constructor[0].newInstance("ГАЗ-311055");
        System.out.println(gaz); //вернёт Car{name='ГАЗ-311055'}


        //В мэйн я добавил ещё один класс Reflection
        //API это класс Field. В нём можно хранить поля данных анализируемого нами класса.
        //Метод getField() возвращает список только публичных полей
        Field[] fields = gaz.getClass().getFields();

        //я записал в tmp просто текущее значение последнего поля
        //Для примитивных типов в классе Field определены специальные геттеры, чтобы привидением типов не заниматься!
        int tmp = fields[fields.length - 1].getInt(gaz);

        //А для установки новых значений есть в классе Field метод set, для примитивных типов специальные сеттеры.
        //Им мы воспользовались для обновления значения поля maxSpeed!
        fields[fields.length - 1].setInt(gaz, tmp + 100);


        //метод getDeclaredField() и получаю список полей и публичных и приватных
        //методом getName() нахожу поле с именем "price"
        //Методом setAccessible() я могу сделать поле публичным, true, или снова приватным false.
        Field[] fieldsAll = gaz.getClass().getDeclaredFields();
        for (Field field : fieldsAll) {
            if (field.getName().equals("price")) {
                field.setAccessible(true);
                field.set(gaz, "Доступно!");
                field.setAccessible(false);
            }
        }

        //У нас появился ещё один класс из Reflection API, это класс methods. Он описывает
        //методы, и заполнить его можно вызвав методы getClass() и getMethods() нашего
        //объекта. Массив methods заполнится всеми доступными методами, и вот что мы
        //увидим в консоли.
        Method[]methods = gaz.getClass().getMethods();
        for (Method method : methods) {
            System.out.println(method); //выведет все методы, включая унаследованные
        }
        //getMethods(),как и getField(), возвращает все публичные методы и поля объявленные
        //непосредственно в классе и унаследованные из супер классов тоже!


        //А давайте уберём наследие и оставим только свои методы.
        Method[] methodsMy = gaz.getClass().getDeclaredMethods();
        for (int i = 0; i < methodsMy.length; i++) {
            System.out.println(methods[i]); //Остались только объявленные в классе Car методы и приватный метод setPrice()!
        }
    }
}
