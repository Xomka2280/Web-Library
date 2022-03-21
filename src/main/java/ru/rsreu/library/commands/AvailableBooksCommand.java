package ru.rsreu.library.commands;

import ru.rsreu.library.dao.BookDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AvailableBooksCommand extends FrontCommand {
    private BookDAO bookDAO;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        bookDAO = daoFactory.getBookDAO();
    }

    @Override
    public void process() throws ServletException, IOException {
        request.setAttribute("availableBooks", bookDAO.getAvailableBooks());
        request.setAttribute("link", request.getPathInfo().substring(1));

        forward("books");
    }
}
