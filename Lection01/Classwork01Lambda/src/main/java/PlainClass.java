
/*todo
        Давайте создадим простой класс. В качестве точки входа используем метод main,
    реализуем интерфейс PlainInterface и создадим метод action непосредственно в
    классе. В Java такое решение используется нечасто, обычно вместо этого мы
    добавляем статичный метод.
 */


public class PlainClass {

    public static void main(String[] args) {
        PlainInterface plainInterface = new PlainInterface() {
            @Override
            public String action(int x, int y) {
                return String.valueOf(x + y);
            }
        };
        System.out.println(plainInterface.action(5, 5));

        /*Но давайте сначала перепишем реализацию с использованием лямбда выражения.
        Изменился синтаксис, и код стал проще. Не нужно создавать экземпляр класса, не
        нужна аннотация @Override и, если посмотреть внимательней, мы не передаем типы
        параметров и не указываем ключевое слово return. Всё это делает компилятор!
        */

        PlainInterface plainInterface1 = ((x, y) -> String.valueOf(x + y));
        System.out.println(plainInterface1.action(3, 3));


        PlainInterface plainInterface2 = (((x, y) -> {
            String str = String.valueOf(x+y);
            return str + "!";
        }));
        System.out.println(plainInterface2.action(2,2));
    }
}


/*
    И ещё обращу ваше внимание на новую аннотацию
    @FunctionalInterface. Она проверяет, является ли интерфейс функциональным, то
    есть описывает всего один метод. Это обязательно для лямбда выражений и если
    это не так, аннотация просто не даст коду собраться.
 */
@FunctionalInterface
interface PlainInterface { //вложенный интерфейс
    String action(int x, int y);
}
