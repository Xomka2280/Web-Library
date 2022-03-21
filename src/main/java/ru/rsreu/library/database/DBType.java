package ru.rsreu.library.database;

import ru.rsreu.library.dao.DAOFactory;
import ru.rsreu.library.oracle.OracleDBDAOFactory;

public enum DBType {

    ORACLE {

        @Override
        public DAOFactory getDAOFactory() {
            return OracleDBDAOFactory.getInstance();
        }

    };

    public abstract DAOFactory getDAOFactory();

}
