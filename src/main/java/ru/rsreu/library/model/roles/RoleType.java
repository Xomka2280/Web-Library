package ru.rsreu.library.model.roles;

import ru.rsreu.library.dao.DAOFactory;
import ru.rsreu.library.database.DBType;

public enum RoleType {
    ADMIN(1), MODERATOR(2), READER(3), LIBRARIAN(4);

    private final int id;

    RoleType(int id) {
        this.id = id;
    }

    public Role getRole() {
        return getRoleById(id);
    }

    public int getId() {
        return this.id;
    }

    public static Role getRole(int id) {
        return getRoleById(id);
    }

    private static Role getRoleById(int id) {
        return DAOFactory.getInstance(DBType.ORACLE).getRoleDAO().getRoleById(id);
    }
}
