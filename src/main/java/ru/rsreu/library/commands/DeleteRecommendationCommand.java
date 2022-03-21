package ru.rsreu.library.commands;

import ru.rsreu.library.dao.RecommendDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteRecommendationCommand extends FrontCommand{
    private RecommendDAO recommendDAO;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);
        recommendDAO = daoFactory.getRecommendDAO();
    }

    @Override
    public void send() throws ServletException, IOException {
        String id = request.getParameter("recommendationId");
        recommendDAO.deleteRecommend(Integer.parseInt(id));
    }
}
