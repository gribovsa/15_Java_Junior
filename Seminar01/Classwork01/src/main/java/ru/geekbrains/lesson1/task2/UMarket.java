package ru.geekbrains.lesson1.task2;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Магазин U-Market
 */

public class UMarket {

    //region Конструкторы
    public UMarket() {
        things = new ArrayList<>();
        initializeThings();
    }
    //endregion

    private void initializeThings(){
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
    //region Поля
    /**
     * Товары в магазине
     */
    private final Collection<Thing>things;
    //endregion
}

//1:31:28