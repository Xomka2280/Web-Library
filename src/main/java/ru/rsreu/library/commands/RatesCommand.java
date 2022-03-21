package ru.rsreu.library.commands;

import ru.rsreu.library.dao.BookDAO;
import ru.rsreu.library.dao.RateDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RatesCommand extends FrontCommand{
    private RateDAO rateDAO;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);
        rateDAO = daoFactory.getRateDAO();
    }

    @Override
    public void process() throws ServletException, IOException {
        request.setAttribute("rates", rateDAO.findAll());
        forward("rates");
    }
}
