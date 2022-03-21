package ru.rsreu.library.dao;

import ru.rsreu.library.model.books.Book;
import ru.rsreu.library.model.books.Recommendation;
import ru.rsreu.library.model.users.User;

import java.util.ArrayList;

public interface RecommendDAO {

    void recommendBook(User user, Book book, User sender, String text);

    void deleteRecommend(int id);

    ArrayList<Recommendation> findAll();

}
