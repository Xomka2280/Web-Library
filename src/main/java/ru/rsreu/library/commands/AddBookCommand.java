package ru.rsreu.library.commands;

import ru.rsreu.library.dao.BookDAO;
import ru.rsreu.library.model.books.Book;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddBookCommand extends FrontCommand {

    private BookDAO bookDAO;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        bookDAO = daoFactory.getBookDAO();
    }

    @Override
    public void send() throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");

        Book book = new Book(title, author, 0);

        bookDAO.addBook(book);
    }
}
