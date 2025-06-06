package org.gribov.models;

import jakarta.persistence.*;

/**
 * Класс "Курс"
 */

//для Hibernate (указание сущности и название таблицы)
@Entity
@Table(name = "courses")
public class Course {

    //для Hibernate (уникальный идентификатор)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "название")
    private String title; //название курса

    @Column(name = "продолжительность")
    private int duration; //продолжительность в учебных часах

    //конструктор по умолчанию, требуется для Hibernate
    public Course() {
    }

    //region Конструкторы
    public Course(int id, String title, int duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public Course(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }
    //endregion


    // region Геттеры и Сеттеры

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    //endregion


    @Override
    public String toString() {
        return "Курс{" +
                "id=" + id +
                ", название='" + title + '\'' +
                ", продолжительность=" + duration +
                '}';
    }
}
