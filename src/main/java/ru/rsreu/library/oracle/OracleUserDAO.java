package ru.rsreu.library.oracle;

import com.prutzkow.resourcer.ProjectResourcer;
import ru.rsreu.library.dao.UserDAO;
import ru.rsreu.library.database.ConnectionPool;
import ru.rsreu.library.model.roles.RoleType;
import ru.rsreu.library.model.users.User;
import ru.rsreu.library.utils.NumericHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OracleUserDAO implements UserDAO {
    private static final String SELECT_USER_BY_LOGIN = ProjectResourcer.getInstance().getString("db.query.select.user.by.login");
    private static final String SELECT_USER_BY_ID = ProjectResourcer.getInstance().getString("db.query.select.user.by.id");
    private static final String SELECT_ALL_USERS = ProjectResourcer.getInstance().getString("db.query.select.all.users");
    private static final String SELECT_READERS = ProjectResourcer.getInstance().getString("db.query.select.readers");
    private static final String DELETE_USER = ProjectResourcer.getInstance().getString("db.query.delete.user");
    private static final String UPDATE_USER = ProjectResourcer.getInstance().getString("db.query.update.user");
    private static final String INSERT_USER = ProjectResourcer.getInstance().getString("db.query.insert.user");
    private static final String SELECT_ALL_WITHOUT_USER = ProjectResourcer.getInstance().getString("db.query.select.all.users.without.user");

    @Override
    public ArrayList<User> findAll() {
        ArrayList<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(createUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public ArrayList<User> findAllWithoutUser(User user) {
        ArrayList<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_WITHOUT_USER);
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                users.add(createUser(resultSet));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return users;
    }

    @Override
    public ArrayList<User> findReaders(User user) {
        ArrayList<User> readers = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_READERS);
            preparedStatement.setInt(1, RoleType.READER.getId());
            preparedStatement.setInt(2, user.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                readers.add(createUser(resultSet));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return readers;
    }

    @Override
    public User getUserById(int id) {
        User user = null;

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user = giveUser(rs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return user;
    }

    @Override
    public User getUserById(String id) {
        return getUserById(Integer.parseInt(id));
    }

    @Override
    public User getUserByLogin(String login) {
        User user = null;

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN);
            preparedStatement.setString(1, login);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = createUser(rs);
            }

            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return user;
    }

    @Override
    public void updateUser(User user) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
            insertUser(preparedStatement, user);

            preparedStatement.setInt(6, user.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addUser(User user) {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);

            insertUser(preparedStatement, user);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setInt(1, user.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    private User createUser(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                RoleType.getRole(resultSet.getInt(4)), NumericHelper.convertToBool(resultSet.getInt(5)),
                NumericHelper.convertToBool(resultSet.getInt(6)));
    }

    private User giveUser(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getInt("user_id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(RoleType.getRole(resultSet.getInt("role_id")));
        user.setBlocked(NumericHelper.convertToBool(resultSet.getInt("blocked")));
        user.setStatusAuthorize(NumericHelper.convertToBool(resultSet.getInt("status")));

        return user;
    }

    private void insertUser(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setInt(3, user.getRole().getId());
        preparedStatement.setInt(4, NumericHelper.convertToInt(user.isBlocked()));
        preparedStatement.setInt(5, NumericHelper.convertToInt(user.isAuthorized()));
    }
}
