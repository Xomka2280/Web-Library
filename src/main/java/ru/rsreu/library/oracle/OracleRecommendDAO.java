package ru.rsreu.library.oracle;

import com.prutzkow.resourcer.ProjectResourcer;
import ru.rsreu.library.dao.BookDAO;
import ru.rsreu.library.dao.DAOFactory;
import ru.rsreu.library.dao.RecommendDAO;
import ru.rsreu.library.dao.UserDAO;
import ru.rsreu.library.database.ConnectionPool;
import ru.rsreu.library.database.DBType;
import ru.rsreu.library.model.books.Book;
import ru.rsreu.library.model.books.Recommendation;
import ru.rsreu.library.model.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OracleRecommendDAO implements RecommendDAO {

    private static final String INSERT_RECOMMENDATION = ProjectResourcer.getInstance().getString("db.query.insert.recommendation");
    private static final String DELETE_RECOMMENDATION = ProjectResourcer.getInstance().getString("db.query.delete.recommendation");
    private static final String ALL_RECOMMENDATIONS = ProjectResourcer.getInstance().getString("db.query.select.all.recommendations");

    private static final DAOFactory daoFactory;

    static {
        daoFactory = DAOFactory.getInstance(DBType.ORACLE);
    }

    @Override
    public ArrayList<Recommendation> findAll() {
        ArrayList<Recommendation> recommendations = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ALL_RECOMMENDATIONS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                recommendations.add(createRecommendation(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recommendations;
    }

    @Override
    public void recommendBook(User user, Book book, User sender, String text) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RECOMMENDATION);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, sender.getId());
            preparedStatement.setInt(3, book.getId());
            preparedStatement.setString(4, text);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteRecommend(int id) {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RECOMMENDATION);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Recommendation createRecommendation(ResultSet resultSet) throws SQLException {
        UserDAO userDAO = daoFactory.getUserDAO();
        BookDAO bookDAO = daoFactory.getBookDAO();
        return new Recommendation(resultSet.getInt(1), userDAO.getUserByLogin(resultSet.getString(2)),
                userDAO.getUserByLogin(resultSet.getString(3)), bookDAO.getBookById(resultSet.getInt(4)), resultSet.getString(5));
    }
}
