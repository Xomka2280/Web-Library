package ru.rsreu.library.listener;

import ru.rsreu.library.dao.DAOFactory;
import ru.rsreu.library.dao.UserDAO;
import ru.rsreu.library.database.DBType;
import ru.rsreu.library.model.users.User;
import ru.rsreu.library.utils.AppUtils;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class TimeoutSessionListener implements HttpSessionListener {

    private static final int MAX_INACTIVE_INTERVAL = 60*60;

    private final UserDAO userDAO = DAOFactory.getInstance(DBType.ORACLE).getUserDAO();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();

        session.setMaxInactiveInterval(MAX_INACTIVE_INTERVAL);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        User user = AppUtils.getLoginUser(session);

        user.setStatusAuthorize(false);

        userDAO.updateUser(user);
    }
}
