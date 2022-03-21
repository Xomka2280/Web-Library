package ru.rsreu.library.commands;

import ru.rsreu.library.dao.NoticesDAO;
import ru.rsreu.library.model.books.Notice;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteNoticeCommand extends FrontCommand {

    private NoticesDAO noticesDAO;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        noticesDAO = daoFactory.getNoticesDAO();
    }

    @Override
    public void send() throws ServletException, IOException {
        String noticeId = request.getParameter("noticeId");

        Notice notice = noticesDAO.getNoticeById(noticeId);

        noticesDAO.deleteNotice(notice);
    }
}
