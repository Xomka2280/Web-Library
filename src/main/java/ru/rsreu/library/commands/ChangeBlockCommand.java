package ru.rsreu.library.commands;

import ru.rsreu.library.dao.UserDAO;
import ru.rsreu.library.model.users.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeBlockCommand extends FrontCommand {

    private UserDAO userDAO;


    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        userDAO = daoFactory.getUserDAO();
    }

    @Override
    public void send() throws ServletException, IOException {
        String userId = request.getParameter("id");
        User user = userDAO.getUserById(userId);

        if (user == null) return;

        user.setBlocked(!user.isBlocked());
        userDAO.updateUser(user);
    }
}
