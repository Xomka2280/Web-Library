package ru.rsreu.library.commands;

import ru.rsreu.library.dao.RecommendDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RecommendationsCommand extends FrontCommand{
    private RecommendDAO recommendDAO;
    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);
        recommendDAO = daoFactory.getRecommendDAO();
    }

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        request.setAttribute("recommendations", recommendDAO.findAll());
        forward("recommendations");
    }
}
