package ru.rsreu.library.dao;


import ru.rsreu.library.model.users.User;

import java.util.ArrayList;

/**
 * Interaction interface Databases and tables Users
 */
public interface UserDAO {

    /**
     * Method that returns all users from the table Users as an ArrayList
     *
     * @return array of users objects
     */
    ArrayList<User> findAll();

    ArrayList<User> findAllWithoutUser(User user);

    ArrayList<User> findReaders(User user);

    /**
     * The method returns an object of the User class found in the table by a unique identifier of type int
     *
     * @param id a unique identifier that identifies a record in a table
     * @return a User object on success, or null if no user is found.
     */
    User getUserById(int id);

    /**
     * The method returns an object of the User class found in the table by a unique identifier of type String
     *
     * @param id a unique identifier that identifies a record in a table
     * @return a User object on success, or null if no user is found.
     */
    User getUserById(String id);

    /**
     * The method returns an object of the User class found in the table by login
     *
     * @param login string value of the login attribute in the Users table
     * @return a User object on success, or null if no user is found.
     */
    User getUserByLogin(String login);

    /**
     * @param user
     */
    void updateUser(User user);

    /**
     * The method adds a record to the Notes table
     *
     * @param user object by which it defines a record add in the table
     */
    void addUser(User user);

    /**
     * Deletes the entry in the Users table corresponding to the given user.
     *
     * @param user the object by which the entry to be deleted is determined
     */
    void deleteUser(User user);

}
