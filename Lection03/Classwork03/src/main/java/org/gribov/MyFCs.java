package org.gribov;

import java.io.Serial;
import java.io.Serializable;

/**
 * Это обычный класс на языке Java с моими ФИО, конструктором и
 * переопределенным методом toString().
 */

public class MyFCs implements Serializable {
    /*
    Любой класс, который мы хотим сериализовать, должен реализовывать интерфейс
    Serializable. И вот интересный момент: для этого интерфейса не нужно
    реализовывать ни одного метода. Он всего лишь сообщает системе, что класс может
    быть сериализован. Маркерный интерфейс.
    */
    @Serial
    private static final long serialVersionUID = 1L;

    private final String lName;
    private final String fName;
    private final String patronymic;
    private final Dob dob;

    public MyFCs(String fName, String lName, String patronymic) {
        this.lName = lName;
        this.fName = fName;
        this.patronymic = patronymic;
        this.dob = new Dob(9, 3, 1981);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s.%s. %s",
                serialVersionUID,
                fName,
                lName.toUpperCase().charAt(0),
                patronymic.toUpperCase().charAt(0),
                dob.day + "/" + dob.month + "/" + dob.year);
    }
}

