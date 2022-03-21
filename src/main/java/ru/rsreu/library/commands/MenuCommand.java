package ru.rsreu.library.commands;

import ru.rsreu.library.model.users.User;
import ru.rsreu.library.utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MenuCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        User user = AppUtils.getLoginUser(session);

        if (user != null) forward("menu");
        else redirect("login");
    }
}
