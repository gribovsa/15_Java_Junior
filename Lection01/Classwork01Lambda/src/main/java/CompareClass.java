public class CompareClass {

    public static void main(String[] args) {
        SumInterface sumInterface = ((x, y) -> String.valueOf(x + y));
        System.out.println(sumInterface.action(5,5));


        /*
            В этой реализации нет переменных, только имя класса и имя метода,
        разделенные двумя двоеточиями. Как это работает? Метод compare класса Integer
        принимает два int параметра и возвращает int значение.
         */
        CompareInterface compareInterface = Integer::compare;
        System.out.println(compareInterface.action(5,15));

        /*
            Если бы мы описали это с
        помощью обычного лямбда-выражения, то получили бы следующий код.
         */
        CompareInterface compareInterface1 = ((x, y) -> Integer.compare(x,y));
        System.out.println(compareInterface1.action(5,15));
    }
}



@FunctionalInterface
interface SumInterface{
    String action(int x,int y);
}

@FunctionalInterface
interface CompareInterface{
    int action(int x, int y);
}