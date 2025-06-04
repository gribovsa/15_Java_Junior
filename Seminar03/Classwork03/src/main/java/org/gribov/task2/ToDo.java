package org.gribov.task2;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * В этом классе вместо implements Serializable используем служебный интерфейс implements Externalizable,
 * при этом переопределяем методы сериализации данных writeExternal readExternal
 * это полезная вещь, мы можем вручную задать логику сериализации, какие поля сериализировать,
 * какие нет.
 */


public class ToDo implements Externalizable {

    private static final long serialVersionUID = 1L;

    //region Поля

    /**
     * Наименование задачи
     */
    private String title;

    /**
     * Статус задачи
     */
    private boolean isDone;

    //endregion

    public ToDo() {
    }

    public ToDo(String title) {
        this.title = title;
        isDone = false;
    }

    //region Externalizable implementation
    //ручной процесс сериализации данных

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(title);
        out.writeBoolean(isDone);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        title = (String) in.readObject();
        isDone = in.readBoolean();
    }

    //endregion

    //region Методы

    /**
     * Получить наименование задачи
     * @return наименование задачи
     */
    public String getTitle() {
        return title;
    }

    /**
     * Получить статус выполнения задачи
     * @return статус выполнения задачи
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Изменить статус выполнения задачи
     * @param done новый статус задачи
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    //endregion

}
