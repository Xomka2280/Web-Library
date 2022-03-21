package ru.rsreu.library.model.users;

import ru.rsreu.library.model.Entity;
import ru.rsreu.library.model.roles.Role;

import java.util.Objects;

public class User extends Entity {

    private String login;
    private String password;
    private Role role;
    private boolean blocked;
    private boolean status;

    public User() {}

    public User(String login, String password, Role role, boolean blocked, boolean status){
        this.login = login;
        this.password = password;
        this.role = role;
        this.blocked = blocked;
        this.status = status;
    }

    public User(int id, String login, String password, Role role, boolean blocked, boolean status) {
        super(id);
        this.login = login;
        this.password = password;
        this.role = role;
        this.blocked = blocked;
        this.status = status;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isAuthorized() {
        return status;
    }

    public void setStatusAuthorize(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return isBlocked() == user.isBlocked() && status == user.status && Objects.equals(getLogin(), user.getLogin()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getRole(), user.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getLogin(), getPassword(), getRole(), isBlocked(), status);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", blocked=" + blocked +
                ", status=" + status +
                '}';
    }
}
