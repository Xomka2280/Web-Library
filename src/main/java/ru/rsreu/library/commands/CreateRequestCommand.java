package ru.rsreu.library.commands;

import ru.rsreu.library.dao.BookDAO;
import ru.rsreu.library.dao.NoticesDAO;
import ru.rsreu.library.model.books.Book;
import ru.rsreu.library.model.books.Notice;
import ru.rsreu.library.model.users.User;
import ru.rsreu.library.utils.AppUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateRequestCommand extends FrontCommand {

    private BookDAO bookDAO;
    private NoticesDAO noticesDAO;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        bookDAO = daoFactory.getBookDAO();
        noticesDAO = daoFactory.getNoticesDAO();
    }

    @Override
    public void send() throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String bookId = request.getParameter("bookId");

        Book book = bookDAO.getBookById(bookId);
        User user = AppUtils.getLoginUser(session);
        Notice notice = createNotice(user, book);

        noticesDAO.createRequest(notice);
        noticesDAO.updateBookNotice(book, false);
    }

    private Notice createNotice(User user, Book book) {
        Notice notice = new Notice();

        notice.setUser(user);
        notice.setBook(book);
        notice.setAvailable(false);

        return notice;
    }
}
