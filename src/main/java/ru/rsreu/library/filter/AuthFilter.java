package ru.rsreu.library.filter;

import ru.rsreu.library.model.users.User;
import ru.rsreu.library.utils.AppUtils;
import ru.rsreu.library.utils.AuthUtils;
import ru.rsreu.library.wrapper.UserRoleRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String pathInfo = request.getPathInfo();

        User user = AppUtils.getLoginUser(request.getSession());

        if (pathInfo.equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        HttpServletRequest wrapRequest = request;

        if (user != null) {
            wrapRequest = new UserRoleRequestWrapper(user, user.getRole(), request);
        }

        if (AuthUtils.isSecurityPage(request)) {

            if (user == null) {
                response.sendRedirect("/library/login");
                return;
            }

            boolean hasPermission = AuthUtils.hasPermission(wrapRequest);

            if (!hasPermission) {
                response.sendRedirect("/library/menu");
                return;
            }
        }

        filterChain.doFilter(wrapRequest, response);
    }
}
