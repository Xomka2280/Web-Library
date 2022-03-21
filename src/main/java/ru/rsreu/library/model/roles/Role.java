package ru.rsreu.library.model.roles;

import ru.rsreu.library.model.Entity;

import java.util.Objects;

public class Role extends Entity {
    private String title;

    /**
     * Instantiates a new Role with all fields
     * @param roleID numeric unique identifier
     * @param title role's title
     */
    public Role(int roleID, String title) {
        super(roleID);
        this.title = title;
    }

    /**
     * Instantiates a new Role
     */
    public Role() {
    }

    /**
     * Gets the role's title
     * @return the title to get
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the role's title
     * @param title to set value
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Compares two objects Role by their content
     * @param o Object to object compares the original object
     * @return true if the objects are equal, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(title, role.title);
    }

    /**
     * Hashes the role name
     * @return a numeric value corresponding to the description of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
