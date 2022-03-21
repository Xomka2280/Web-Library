package ru.rsreu.library.dao;

import ru.rsreu.library.model.roles.Role;

import java.util.ArrayList;

/**
 * Interaction interface Databases and tables Role
 */
public interface RoleDAO {

    /**
     * Method that returns all roles from the table Role as an ArrayList
     * @return array of Role objects
     */
    ArrayList<Role> findAll();

    /**
     * The method returns an object of the Role class found in the table by a unique identifier of type int
     * @param id a unique identifier that identifies a record in a table
     * @return a Role object on success, or null if no role is found.
     */
    Role getRoleById(int id);

    /**
     * The method returns an object of the Role class found in the table by a unique identifier of type String
     * @param id a unique identifier that identifies a record in a table
     * @return a Role object on success, or null if no role is found.
     */
    Role getRoleById(String id);
}
