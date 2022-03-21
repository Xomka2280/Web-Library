package ru.rsreu.library.commands;

import ru.rsreu.library.dao.BookDAO;
import ru.rsreu.library.model.books.Book;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteBookCommand extends FrontCommand {

    private BookDAO bookDAO;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        bookDAO = daoFactory.getBookDAO();
    }

    @Override
    public void send() throws ServletException, IOException {
        String bookId = request.getParameter("bookId");

        Book book = bookDAO.getBookById(bookId);

        bookDAO.deleteBook(book);
    }
}
