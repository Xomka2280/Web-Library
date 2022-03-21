package ru.rsreu.library.oracle;


import com.prutzkow.resourcer.ProjectResourcer;
import ru.rsreu.library.dao.RoleDAO;
import ru.rsreu.library.database.ConnectionPool;
import ru.rsreu.library.model.roles.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OracleRoleDAO implements RoleDAO {
    private static final String SELECT_ALL_ROLES = ProjectResourcer.getInstance().getString("db.query.select.all.roles");
    private static final String SELECT_ROLE_BY_ID = ProjectResourcer.getInstance().getString("db.query.select.role.by.id");

    @Override
    public ArrayList<Role> findAll() {
        ArrayList<Role> roles = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ROLES);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                roles.add(createRole(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roles;
    }

    @Override
    public Role getRoleById(int id) {
        Role role = null;

        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROLE_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role = createRole(resultSet);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return role;
    }

    @Override
    public Role getRoleById(String id) {
        return getRoleById(Integer.parseInt(id));
    }

    private Role createRole(ResultSet resultSet) throws SQLException {
        return new Role(resultSet.getInt(1), resultSet.getString(2));
    }
}
