package ru.rsreu.library.dao;

import ru.rsreu.library.model.books.Book;
import ru.rsreu.library.model.books.Notice;
import ru.rsreu.library.model.users.User;

import java.util.ArrayList;

public interface NoticesDAO {

    ArrayList<Notice> getNoticesByOwner(User user);

    Notice getNoticeById(int id);

    Notice getNoticeById(String id);

    void updateBookNotice(Book book, boolean available);

    void createRequest(Notice notice);

    void deleteNotice(Notice notice);

}
