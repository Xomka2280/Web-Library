package ru.rsreu.library.wrapper;

import ru.rsreu.library.model.roles.Role;
import ru.rsreu.library.model.users.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.security.Principal;

public class UserRoleRequestWrapper extends HttpServletRequestWrapper {

    private final User user;
    private final Role role;
    private final HttpServletRequest request;

    public UserRoleRequestWrapper(User user, Role role, HttpServletRequest request) {
        super(request);
        this.user = user;
        this.role = role;
        this.request = request;
    }

    @Override
    public boolean isUserInRole(String role) {
        if (role == null) {
            return this.request.isUserInRole(null);
        }

        return this.role.getTitle().equalsIgnoreCase(role);
    }

    @Override
    public Principal getUserPrincipal() {
        if (this.user == null) {
            return this.request.getUserPrincipal();
        }

        return user::getLogin;
    }
}