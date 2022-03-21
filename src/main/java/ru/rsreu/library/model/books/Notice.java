package ru.rsreu.library.model.books;

import ru.rsreu.library.model.Entity;
import ru.rsreu.library.model.users.User;

import java.util.Objects;

public class Notice extends Entity {

    private User user;
    private Book book;
    private boolean available;

    public Notice() {
    }

    public Notice(int id, User user, Book book, boolean available) {
        super(id);
        this.user = user;
        this.book = book;
        this.available = available;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean status) {
        this.available = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notice)) return false;
        if (!super.equals(o)) return false;
        Notice notice = (Notice) o;
        return available == notice.available && Objects.equals(user, notice.user) && Objects.equals(book, notice.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, book, available);
    }
}
