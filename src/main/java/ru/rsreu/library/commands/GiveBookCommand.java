package ru.rsreu.library.commands;

import ru.rsreu.library.dao.BookDAO;
import ru.rsreu.library.model.users.User;
import ru.rsreu.library.utils.AppUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GiveBookCommand extends FrontCommand {

    private BookDAO bookDAO;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        bookDAO = daoFactory.getBookDAO();
    }

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = AppUtils.getLoginUser(session);

        request.setAttribute("takenBooks", bookDAO.getTakenBooks(user));

        forward("giveBook");
    }
}
