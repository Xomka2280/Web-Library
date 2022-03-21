package ru.rsreu.library.oracle;

import ru.rsreu.library.dao.*;

public class OracleDBDAOFactory extends DAOFactory {

    private static volatile OracleDBDAOFactory instance;

    private OracleDBDAOFactory() {

    }

    public static OracleDBDAOFactory getInstance() {
        if (instance == null) {
            synchronized (OracleDBDAOFactory.class) {
                instance = new OracleDBDAOFactory();
            }
        }
        return instance;
    }

    @Override
    public UserDAO getUserDAO() {
        return new OracleUserDAO();
    }

    @Override
    public RoleDAO getRoleDAO() {
        return new OracleRoleDAO();
    }

    @Override
    public BookDAO getBookDAO() {
        return new OracleBookDAO();
    }

    @Override
    public RecommendDAO getRecommendDAO() {
        return new OracleRecommendDAO();
    }

    @Override
    public NoticesDAO getNoticesDAO() {
        return new OracleNoticesDAO();
    }

    @Override
    public RateDAO getRateDAO() { return new OracleRateDAO(); }
}
