package ru.rsreu.library.model.books;

import ru.rsreu.library.model.Entity;
import ru.rsreu.library.model.users.User;

import javax.jws.soap.SOAPBinding;
import java.util.Objects;

public class Recommendation extends Entity {
    private User reader;
    private User sender;
    private Book book;
    private String text;

    public Recommendation(int id, User reader, User sender, Book book, String text) {
        super(id);
        this.reader = reader;
        this.sender = sender;
        this.book = book;
        this.text = text;
    }

    public User getReader() {
        return reader;
    }

    public void setReader(User reader) {
        this.reader = reader;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recommendation)) return false;
        if (!super.equals(o)) return false;
        Recommendation that = (Recommendation) o;
        return Objects.equals(getReader(), that.getReader()) && Objects.equals(getSender(), that.getSender()) && Objects.equals(getBook(), that.getBook()) && Objects.equals(getText(), that.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getReader(), getSender(), getBook(), getText());
    }
}
