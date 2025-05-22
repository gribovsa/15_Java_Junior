package CollectorsExample;

/*todo
    В более сложном примере я описал каждый шаг подробно.
    Months — это просто перечисление месяцев для удобного вывода.
    PersonID — это простой класс, описывающий сотрудника с закрытыми полями:
    id, ФИО, датой рождения, зарплатой и геттерами.
    В методе main инициализируется список personIDS и генерируется случайная зарплата.
    Затем создается поток данных из списка personIDS и преобразуется в поток дат рождения
    с использованием лямбда-метода.
    Метод collect с параметром Collectors.toList собирает все полученные данные в
    список, который сохраняется в result.
 */




import java.util.*;
import java.util.stream.Collectors;

public class Str {
    public static void main(String[] args) {
        List<PersonID> personIDS = Arrays.asList(
                new PersonID("Иванов И.И.", new Date(1992, Calendar.FEBRUARY, 7),
                        genSalary()),
                new PersonID("Петров П.В.", new Date(1987, Calendar.APRIL , 27),
                        genSalary()),
                new PersonID("Селиванов В.А.", new Date(1995, Calendar.AUGUST, 15),
                        genSalary()),
                new PersonID("Кладовцева Я.И.", new Date(1996, Calendar.JUNE, 28),
                        genSalary()),
                new PersonID("Стильнов В.М.", new Date(1981, Calendar.SEPTEMBER, 18),
                        genSalary()),
                new PersonID("Иванова С.В.", new Date(1991, Calendar.FEBRUARY, 17),
                        genSalary()),
                new PersonID("Одоевцева М.В.", new Date(2001, Calendar.JANUARY, 6),
                        genSalary()),
                new PersonID("Кузеванов А.И.", new Date(2003, Calendar.JUNE, 14),
                        genSalary()),
                new PersonID("Донцев Ю.Ф.", new Date(1991, Calendar.MAY, 22),
                        genSalary()),
                new PersonID("Кривцова А.И.", new Date(1976, Calendar.DECEMBER, 4),
                        genSalary()),
                new PersonID("Бронникова И.И.", new Date(1999, Calendar.OCTOBER, 19),
                        genSalary()),
                new PersonID("Остафьев И.А.", new Date(1995, Calendar.FEBRUARY, 24),
                        genSalary())
        );

        System.out.println("Метод collect с параметром Collectors.toList собирает даты в новый список.");

        List<String> tmpList = personIDS.stream().map(n ->
                n.getDOB()).collect(Collectors.toList());

        System.out.println(tmpList);
        System.out.println("\n");


        /*
        В этом примере я добавил только фильтр. Я выбираю из потока только тех, кто
        родился после 1 января 1995 года. Из исходного списка в 12 человек были выбраны
        только 6.
        Метод map проходит по всем элементам и возвращает в поток данные изменённые по логике
        его лямбда выражения.
         */
        List<String> tmpList1 = personIDS.stream()
                .filter(n -> n.getDATE().compareTo(new Date(1995, Calendar.JANUARY, 1))>0)
                .map(PersonID::getDOB).collect(Collectors.toList());
        System.out.println(tmpList1);
        System.out.println("\n");


        /*
        Можно усложнить задачу, добавив еще один фильтр.
        Теперь мы собираем имена сотрудников и сортируем их по уровню зарплаты.
        Результат - список сотрудников с именами, родившимися после 1995 года, отсортированными по
        зарплате.
         */
        List<String> tmpList2 = personIDS.stream()
                .filter(n -> n.getDATE().compareTo(new Date(1995, Calendar.JANUARY, 1))>0) //фильтруем по 1995г
                .sorted((a,b)-> (int) (a.getSalary()-b.getSalary())) //сортируем по уровню зарплаты используя компаратор
                .map(n -> n.getFIO() + " (" + n.getDOB() + ") " + n.getSalary())//изменяем выходные данные как нам надо
                .limit(5)//берем первые 5 из результата
                .collect(Collectors.toList()); //выводим результат в tmpList2

        tmpList2.forEach(n-> System.out.println(n));






    }
    private static int genSalary(){return new Random().nextInt(63758)+16242;}
}
