import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    /*todo
        Напишите программу, которая использует Stream API для обработки списка чисел.
        Программа должна вывести на экран среднее значение всех четных чисел в списке.
     */
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,14));
        Double average = list.stream().filter(n -> n % 2 == 0).collect(Collectors.averagingInt((p) -> p));
        System.out.println(average);
    }
}
