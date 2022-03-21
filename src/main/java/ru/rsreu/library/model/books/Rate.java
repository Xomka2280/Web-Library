package ru.rsreu.library.model.books;

import ru.rsreu.library.model.Entity;
import ru.rsreu.library.model.users.User;

import java.util.Objects;

public class Rate extends Entity {
    private User reader;
    private Book book;
    private int rating;

    public Rate(int id, User reader, Book book, int rating) {
        super(id);
        this.reader = reader;
        this.book = book;
        this.rating = rating;
    }

    public User getReader() {
        return reader;
    }

    public void setReader(User reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rate)) return false;
        if (!super.equals(o)) return false;
        Rate rate = (Rate) o;
        return getRating() == rate.getRating() && Objects.equals(getReader(), rate.getReader()) && Objects.equals(getBook(), rate.getBook());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getReader(), getBook(), getRating());
    }
}
