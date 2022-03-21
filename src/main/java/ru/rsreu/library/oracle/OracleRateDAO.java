package ru.rsreu.library.oracle;

import com.prutzkow.resourcer.ProjectResourcer;
import oracle.jdbc.proxy.annotation.Pre;
import ru.rsreu.library.dao.BookDAO;
import ru.rsreu.library.dao.DAOFactory;
import ru.rsreu.library.dao.RateDAO;
import ru.rsreu.library.dao.UserDAO;
import ru.rsreu.library.database.ConnectionPool;
import ru.rsreu.library.database.DBType;
import ru.rsreu.library.model.books.Rate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OracleRateDAO implements RateDAO {
    private static final String SELECT_ALL = ProjectResourcer.getInstance().getString("db.query.select.all.rates");
    private static final String DELETE_RATE = ProjectResourcer.getInstance().getString("db.query.delete.rate");
    private static final String SELECT_AVG_RATES_BOOKS =  ProjectResourcer.getInstance().getString("db.query.select.avg.rates.books");
    private static final DAOFactory daoFactory;

    static {
        daoFactory = DAOFactory.getInstance(DBType.ORACLE);
    }

    @Override
    public Map<String, Float> findAvgRates() {
        Map<String, Float> map = new HashMap<>();

        try(Connection connection = ConnectionPool.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AVG_RATES_BOOKS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                map.put(resultSet.getString(1), resultSet.getFloat(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map;
    }

    @Override
    public ArrayList<Rate> findAll() {
        ArrayList<Rate> rates = new ArrayList<>();

        try(Connection connection = ConnectionPool.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                rates.add(createRate(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rates;
    }

    @Override
    public void deleteRate(int id) {
        try(Connection connection = ConnectionPool.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RATE);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Rate createRate(ResultSet resultSet) throws SQLException {
        UserDAO userDAO = daoFactory.getUserDAO();
        BookDAO bookDAO = daoFactory.getBookDAO();
        return new Rate(resultSet.getInt(1), userDAO.getUserById(resultSet.getInt(2)),
                bookDAO.getBookById(resultSet.getInt(3)), resultSet.getInt(4));
    }
}
