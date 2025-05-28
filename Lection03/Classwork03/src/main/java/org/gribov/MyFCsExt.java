package org.gribov;

/*todo
        Давайте еще познакомимся
    с одним интерфейсом сериализации - Externalizable! Это также интерфейс
    сериализации и в целом работает похожим образом.
        Используется, если данные мы хотим их зашифровать
        Изменений, собственно, много — те же поля данных, но без
    модификаторов final.
 */

import java.io.*;

public class MyFCsExt implements Externalizable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String lName;
    private String fName;
    private String patronymic;
    private transient Dob dob;

    /*
        Требуется обязательный конструктор по умолчанию (без параметров), а также два новых
        метода: writeExternal и readExternal — это и есть реализация интерфейса
        Externalizable.
     */
    public MyFCsExt(){}

    public MyFCsExt(String fName, String lName, String patronymic) {
        this.lName = lName;
        this.fName = fName;
        this.patronymic = patronymic;
        this.dob = new Dob(1, 2, 13);
    }

    @Override
    public String toString() {
        return String.format("%s %s.%s. %s",
                fName, lName.toUpperCase().charAt(0),
                patronymic.toUpperCase().charAt(0),
                dob.day + "/" + dob.month + "/" + dob.year);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException, IOException {
        out.writeObject(this.dob);
        out.writeObject(this.fName);
        out.writeObject(this.patronymic);
        String tmp = lName;
        out.writeObject(tmp);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.dob = (Dob) in.readObject();
        fName = (String) in.readObject();
        this.patronymic = (String) in.readObject();
        lName = (String) in.readObject();
    }

}

