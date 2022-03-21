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

public class EditUserCommand extends FrontCommand{
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
        String id = request.getParameter("id");

        if (id.isEmpty()) {
            redirect("menu");
            return;
        }

        ArrayList<Role> roles = roleDAO.findAll();

        request.setAttribute("roles", roles);

        User user = userDAO.getUserById(id);

        if (user == null) {
            redirect("menu");
            return;
        }

        request.setAttribute("editUser", user);

        forward("editUser");
    }

    @Override
    public void send() throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String id = request.getParameter("id");
        String roleId = request.getParameter("format");

        User user = userDAO.getUserById(id);

        if (user == null) return;

        user.setLogin(login);
        user.setPassword(password);
        user.setRole(roleDAO.getRoleById(roleId));
        userDAO.updateUser(user);

        redirect("users");
    }
}
