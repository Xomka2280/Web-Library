package ru.rsreu.library.dao;

import ru.rsreu.library.model.books.Book;
import ru.rsreu.library.model.users.User;

import java.util.ArrayList;

public interface BookDAO {

    ArrayList<Book> getBooksByOwner(User user);

    ArrayList<Book> getAvailableBooks();

    ArrayList<Book> getTakenBooks(User user);

    ArrayList<Book> findAllBooks();

    Book getBookById(int id);

    Book getBookById(String id);

    void addBook(Book book);

    void updateBook(Book book);

    void deleteBook(Book book);

    void rateBook(User user, Book book, int rate);

}
