package ru.rsreu.library.commands;

import ru.rsreu.library.dao.RateDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RatesBooksCommand extends FrontCommand{
    private RateDAO rateDAO;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);
        rateDAO = daoFactory.getRateDAO();
    }

    @Override
    public void process() throws ServletException, IOException {
        Map<String, Float> map = rateDAO.findAvgRates();
//        map.forEach((k,v)->System.out);
        request.setAttribute("rates", rateDAO.findAvgRates());

        forward("ratesBooks");
    }
}
