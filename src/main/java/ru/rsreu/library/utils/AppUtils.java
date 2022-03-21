package ru.rsreu.library.utils;

import ru.rsreu.library.model.users.User;

import javax.servlet.http.HttpSession;

public class AppUtils {
    public static User getLoginUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }

    public static void storeLoginUser(HttpSession session, User user) {
        session.setAttribute("user", user);
    }
}
