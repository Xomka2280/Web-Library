package ru.rsreu.library.commands;

import ru.rsreu.library.dao.DAOFactory;
import ru.rsreu.library.database.DBType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class FrontCommand {
    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected DAOFactory daoFactory;

    public void init(
            ServletContext servletContext,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse) {
        this.context = servletContext;
        this.request = servletRequest;
        this.response = servletResponse;
        this.daoFactory = DAOFactory.getInstance(DBType.ORACLE);
    }

    public void process() throws ServletException, IOException {

    }

    public void send() throws ServletException, IOException {
    }

    protected void forward(String target) throws ServletException, IOException {
        target = String.format("/pages/%s.jsp", target);
        RequestDispatcher dispatcher = context.getRequestDispatcher(target);
        dispatcher.forward(request, response);
    }

    protected void redirect(String url) throws IOException {
        response.sendRedirect(url);
    }
}