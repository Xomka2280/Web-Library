package ru.rsreu.library.model.books;

import ru.rsreu.library.model.Entity;

import java.util.Objects;

public class Book extends Entity {

    private String title;
    private String author;
    private int ownerId;

    public Book() {
    }

    public Book(String title, String author, int ownerId) {
        this.title = title;
        this.author = author;
        this.ownerId = ownerId;
    }

    public Book(int id, String title, String author, int ownerId) {
        super(id);
        this.title = title;
        this.author = author;
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return ownerId == book.ownerId && Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, author, ownerId);
    }
}
