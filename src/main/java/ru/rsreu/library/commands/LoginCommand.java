package ru.rsreu.library.commands;

import ru.rsreu.library.dao.UserDAO;
import ru.rsreu.library.model.users.User;
import ru.rsreu.library.utils.AppUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand extends FrontCommand {
    private UserDAO userDAO;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        userDAO = daoFactory.getUserDAO();
    }

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = AppUtils.getLoginUser(session);

        if (user != null) {
            redirect("menu");
        } else {
            forward("login");
        }
    }

    @Override
    public void send() throws ServletException, IOException {
        User user = giveAuthorizeUser();

        if (checkAuthorizeUser(user)) {
            forward("login");
        } else {
            authorizeUser(user);
            redirect("menu");
        }
    }

    private void authorizeUser(User user) {
        HttpSession session = request.getSession();
        user.setStatusAuthorize(true);
        userDAO.updateUser(user);
        AppUtils.storeLoginUser(session, user);
    }

    private User giveAuthorizeUser() {
        String login = request.getParameter("login");

        return this.userDAO.getUserByLogin(login);
    }

    private boolean checkAuthorizeUser(User user) {
        String password = request.getParameter("password");
        return user == null || !user.getPassword().equals(password) || user.isBlocked();
    }

}
