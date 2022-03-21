package ru.rsreu.library.utils;

public class JspHelper {
    public static String giveUserStatusBlock(boolean blocked) {
        return blocked ? "Заблокирован" : "Разблокирован";
    }

    public static String giveAuthorizationStatus(boolean authorization) {
        if (authorization) {
            return "Авторизованный";
        } else {
            return "Неавторизованный";
        }
    }

    public static String giveAvailableStatus(boolean available) {
        return available ? "Книга доступна" : "Книга недоступна";
    }

}