
/*todo
    А давайте попробуем создать несколько реализаций!
 */
public class SimpleClass {

    private static SimpleInterface simpleInterface;

    public static void main(String[] args) {
        simpleInterface = ((x, y) -> String.valueOf(x + y)); //реализация метода интерфейса, переопределение
        print(5, 5);
        simpleInterface = ((x, y) -> String.valueOf(x - y));
        print(5, 5);
        simpleInterface = ((x, y) -> String.valueOf(x * y));
        print(5, 5);
    }

    private static void print(int x, int y) {
        System.out.println(simpleInterface.action(x, y));
    }

}


@FunctionalInterface
interface SimpleInterface {
    String action(int x, int y);
}