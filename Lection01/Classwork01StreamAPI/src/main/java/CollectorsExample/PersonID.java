package CollectorsExample;

import java.util.Date;
import java.util.Random;

enum Months {
    Января, Февраля, Марта, Апреля, Мая, Июня, Июля, Августа,
    Сентября, Октября, Ноября, Декабря
}

public class PersonID {
    private final int ID;
    private final String FIO;
    private Date DOB;
    private float salary;

    PersonID(String fio, Date dob, float salary) {
        FIO = fio;
        DOB = dob;
        ID = new Random().nextInt();
        this.salary = salary;
    }

    public int getID() {
        return ID;
    }

    public String getFIO() {
        return FIO;
    }

    public String getDOB() {
        return "" + DOB.getDate() + " " +
                Months.values()[DOB.getMonth()] + " " + DOB.getYear();
    }

    public Date getDATE(){
        return DOB;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
}

