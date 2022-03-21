package ru.rsreu.library.dao;

import ru.rsreu.library.database.DBType;

public abstract class DAOFactory {

    public static DAOFactory getInstance(DBType type) {
        return type.getDAOFactory();
    }

    public abstract UserDAO getUserDAO();

    public abstract RoleDAO getRoleDAO();

    public abstract BookDAO getBookDAO();

    public abstract RecommendDAO getRecommendDAO();

    public abstract NoticesDAO getNoticesDAO();

    public abstract RateDAO getRateDAO();
}
