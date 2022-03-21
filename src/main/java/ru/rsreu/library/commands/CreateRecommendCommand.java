package ru.rsreu.library.commands;

import ru.rsreu.library.dao.BookDAO;
import ru.rsreu.library.dao.RecommendDAO;
import ru.rsreu.library.dao.UserDAO;
import ru.rsreu.library.model.books.Book;
import ru.rsreu.library.model.users.User;
import ru.rsreu.library.utils.AppUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateRecommendCommand extends FrontCommand {

    private BookDAO bookDAO;
    private UserDAO userDAO;
    private RecommendDAO recommendDAO;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        bookDAO = daoFactory.getBookDAO();
        userDAO = daoFactory.getUserDAO();
        recommendDAO = daoFactory.getRecommendDAO();
    }

    @Override
    public void send() throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User sender = AppUtils.getLoginUser(session);

        String bookId = request.getParameter("bookId");
        String userId = request.getParameter("userId");
        String text = request.getParameter("text");

        Book book = bookDAO.getBookById(bookId);
        User user = userDAO.getUserById(userId);

        recommendDAO.recommendBook(user, book, sender, text);
    }
}
