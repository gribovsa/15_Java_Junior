package org.gribov;

import java.io.Serializable;

public class Dob implements Serializable {
    int day;
    int month;
    int year;

    public Dob(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
}
