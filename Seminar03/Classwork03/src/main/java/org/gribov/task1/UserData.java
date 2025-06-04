package org.gribov.task1;

import java.io.Serializable;

public class UserData implements Serializable {
    private String name;
    private int age;
    //пароль не должен хранится в виде сериализированных данных поэтому не будем его сериализировать (transient)
    private transient String password;

    public UserData(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }
}
