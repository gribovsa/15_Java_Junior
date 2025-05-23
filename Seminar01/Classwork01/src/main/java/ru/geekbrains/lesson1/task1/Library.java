package ru.geekbrains.lesson1.task1;

/*todo
    Задача 1: Реализовать систему учёта книг в библиотеке. У каждой книги есть название,
    автор и год издания. Необходимо создать список книг и выполнить следующие действия:
    1. Обычный способ:
        - Найти все книги, написанные определённым автором (например, "John Doe").
        - Найти все книги, изданные после определённого года (например, 2010).
        - Найти все уникальные названия книг в библиотеке.
    2. Использование лямбда-выражений:
        - Те же самые задачи, но с использованием лямбда выражений и
          Stream API для более компактного и выразительного кода.
 */

import java.util.ArrayList;
import java.util.List;

public class Library {
    public static void main(String[] args) {
        List<Book>books=new ArrayList<>();
        books.add(new Book("Преступление и наказание", "Фёдор Достоевский", 1866));
        books.add(new Book("Евгений Онегин", "Александр Пушкин", 1833));
        books.add(new Book("Война и мир", "Лев Толстой", 1869));
        books.add(new Book("Мастер и Маргарита", "Михаил Булгаков", 1967));

        //Поиск книг, написанных автором
        List<Book>authorBook = new ArrayList<>();
        for (Book book : books) {
            if("Лев Толстой".equals(book.getAuthor())){
                authorBook.add(book);
            }
        }
        System.out.println("Лев Толстой: " + authorBook);

        //Поиск книг, изданных после 1866
        List<Book>booksAfterYear = new ArrayList<>();
        for (Book book : books) {
            if(book.getYear() > 1866){
                booksAfterYear.add(book);
            }
        }
        System.out.println("Книги, изданные после 1866: " + booksAfterYear);

        //Уникальные названия книг в библиотеке.
        List<String>uniqueTitles = new ArrayList<>();
        for (Book book : books) {
            if(!uniqueTitles.contains(book.getTitle())){
                uniqueTitles.add(book.getTitle());
            }
        }
        System.out.println("Уникальные названия книг: " + uniqueTitles);
    }
}
