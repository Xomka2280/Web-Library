package ru.rsreu.library.commands;

import ru.rsreu.library.dao.RateDAO;
import ru.rsreu.library.dao.RecommendDAO;
import ru.rsreu.library.model.books.Rate;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteRateCommand extends FrontCommand{
    private RateDAO rateDAO;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);
        rateDAO = daoFactory.getRateDAO();
    }

    @Override
    public void send() throws ServletException, IOException {
        String id = request.getParameter("rateId");
        rateDAO.deleteRate(Integer.parseInt(id));
    }
}
