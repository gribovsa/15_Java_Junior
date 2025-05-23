package ru.geekbrains.lesson1.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Магазин U-Market
 */

public class UMarket {


    //region Методы



    public <T extends Thing> T getThingsByIndex(Class<T> clazz, int index) {
        /*
        int counter = 1;
        for (var thing : things) {
            if (clazz.isAssignableFrom(thing.getClass())) {
                if (index == counter++)
                    return (T) thing;
            }
        }
        return null;
         */

        AtomicInteger counter = new AtomicInteger(1);
        return things.stream()
                .filter(clazz::isInstance)
                .filter(thing->index == counter.getAndIncrement())
                .map(clazz::cast)
                .findFirst()
                .orElse(null); //работает совместно с findFirst
    }



    public <T extends Thing> Collection<T> getThings(Class<T> clazz) {
        /*
        Collection<T>list = new ArrayList<>();
        for (var thing : things) {
            if(clazz.isAssignableFrom(thing.getClass())){
                list.add((T)thing);
            }
        }
        return list;
         */
        return things.stream()
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .collect(Collectors.toList());
    }


    /**
     * Распечатать список товаров по типу
     *
     * @param <T>
     */
    public <T extends Thing> void printThings(Class<T> clazz) {
        //вариант с циклом
        /*
        int index = 1;
        for (var thing : things) {
            if (clazz.isInstance(thing)) { //если получаемый класс является элементом коллекции Things
                if (Food.class.isAssignableFrom(thing.getClass())) { //если элемент коллекции является родителем класса Food
                    System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                            index++,
                            thing.getName(),
                            ((Food) thing).getProteins() ? "Да" : "Нет",
                            ((Food) thing).getFats() ? "Да" : "Нет",
                            ((Food) thing).getCarbohydrates() ? "Да" : "Нет");
                } else {
                    System.out.printf("[%d] %s\n", index++, thing.getName());
                }
            }
        }
        */


        //вариант с лямбдой

        int[] counter = {1}; //сделаем ссылочный тип, доступный StreamAPI

        things.stream()
                .filter(clazz::isInstance)
                .forEach(thing -> {
                    if (Food.class.isAssignableFrom(thing.getClass())) { //если элемент коллекции является родителем класса Food
                        Food foodThing = (Food) thing;
                        System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                                counter[0]++, thing.getName(),
                                foodThing.getProteins() ? "Да" : "Нет",
                                foodThing.getFats() ? "Да" : "Нет",
                                foodThing.getCarbohydrates() ? "Да" : "Нет");
                    } else {
                        System.out.printf("[%d] %s\n", counter[0]++, thing.getName());
                    }
                });
    }


    private void initializeThings() {
        things.add(new Pen());
        things.add(new Notebook());

        things.add(new Chicken());
        things.add(new Fruit());
        things.add(new OliveOil());

        things.add(new BalykCheese());
        things.add(new Crisps());
        things.add(new ChocolateBar());

        things.add(new DumplingsBerries());
        things.add(new DumplingsMeat());
        things.add(new Cheburek());
    }
    //endregion

    //region Конструкторы
    public UMarket() {
        things = new ArrayList<>();
        initializeThings();
    }
    //endregion

    //region Поля
    /**
     * Товары в магазине
     */
    private final Collection<Thing> things;
    //endregion
}

//1:31:28