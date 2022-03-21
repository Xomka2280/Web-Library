package ru.rsreu.library.config;

import ru.rsreu.library.model.roles.Role;
import ru.rsreu.library.model.roles.RoleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AuthConfig {

    private static final Map<Role, ArrayList<String>> mapConfig = new HashMap<>();

    static {
        init();
    }

    private static void init() {

        ArrayList<String> userUrlPatterns = new ArrayList<>();
        userUrlPatterns.add("/menu");
        userUrlPatterns.add("/myBooks");
        userUrlPatterns.add("/availableBooks");
        userUrlPatterns.add("/giveBook");
        userUrlPatterns.add("/recommendBook");
        userUrlPatterns.add("/rateBook");
        userUrlPatterns.add("/notices");
        userUrlPatterns.add("/recommendations");
        userUrlPatterns.add("/ratesBooks");

        mapConfig.put(RoleType.READER.getRole(), userUrlPatterns);

        ArrayList<String> moderatorUrlPatterns = new ArrayList<>();
        moderatorUrlPatterns.add("/menu");
        moderatorUrlPatterns.add("/users");
        moderatorUrlPatterns.add("/menu");
        moderatorUrlPatterns.add("/recommendations");
        moderatorUrlPatterns.add("/rates");

        mapConfig.put(RoleType.MODERATOR.getRole(), moderatorUrlPatterns);

        ArrayList<String> librarianUrlPatterns = new ArrayList<>();
        librarianUrlPatterns.add("/menu");
        librarianUrlPatterns.add("/createBook");
        librarianUrlPatterns.add("/books");
        librarianUrlPatterns.add("/recommendBook");

        mapConfig.put(RoleType.LIBRARIAN.getRole(), librarianUrlPatterns);

        ArrayList<String> adminUrlPatterns = new ArrayList<>();
        adminUrlPatterns.add("/menu");
        adminUrlPatterns.add("/users");
        adminUrlPatterns.add("/createUser");
        adminUrlPatterns.add("/editUser");

        mapConfig.put(RoleType.ADMIN.getRole(), adminUrlPatterns);
    }

    public static Set<Role> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static ArrayList<String> getUrlPatternsForRole(Role role) {
        return mapConfig.get(role);
    }
}
