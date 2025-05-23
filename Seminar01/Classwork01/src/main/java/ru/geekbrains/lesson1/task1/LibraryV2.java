package ru.geekbrains.lesson1.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*todo
    Решение с использованием StreamAPI
 */

public class LibraryV2 {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Преступление и наказание", "Фёдор Достоевский", 1866));
        books.add(new Book("Евгений Онегин", "Александр Пушкин", 1833));
        books.add(new Book("Война и мир", "Лев Толстой", 1869));
        books.add(new Book("Мастер и Маргарита", "Михаил Булгаков", 1967));

        //Поиск книг, написанных автором
        List<Book> authorBook = books.stream().filter(book -> "Лев Толстой".equals(book.getAuthor())).toList();
        System.out.println("Лев Толстой: " + authorBook);

        //Поиск книг, изданных после 1866
        List<Book> booksAfterYear = books.stream().filter(book -> book.getYear() > 1866).toList();
        System.out.println("Книги, изданные после 1866: " + booksAfterYear);

        //Уникальные названия книг в библиотеке.
        List<String> uniqueTitles = books.stream().map(Book::getTitle).distinct().toList();
        System.out.println("Уникальные названия книг: " + uniqueTitles);
    }
}
