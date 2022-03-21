package ru.rsreu.library.commands;

import ru.rsreu.library.dao.RoleDAO;
import ru.rsreu.library.dao.UserDAO;
import ru.rsreu.library.model.roles.Role;
import ru.rsreu.library.model.users.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class CreateUserCommand extends FrontCommand{
    private UserDAO userDAO;
    private RoleDAO roleDAO;
    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        userDAO = daoFactory.getUserDAO();
        roleDAO = daoFactory.getRoleDAO();
    }
    @Override
    public void process() throws ServletException, IOException {
        ArrayList<Role> roles = roleDAO.findAll();
        request.setAttribute("roles", roles);

        forward("createUser");
    }
    @Override
    public void send() throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String roleId = request.getParameter("format");

        if (userDAO.getUserByLogin(login) != null) {
            redirect("users");
            return;
        }

        User user = new User(login, password, roleDAO.getRoleById(roleId), false, false);

        userDAO.addUser(user);

        redirect("users");
    }
}
