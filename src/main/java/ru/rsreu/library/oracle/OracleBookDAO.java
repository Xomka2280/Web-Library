package ru.rsreu.library.oracle;

import com.prutzkow.resourcer.ProjectResourcer;
import ru.rsreu.library.dao.BookDAO;
import ru.rsreu.library.database.ConnectionPool;
import ru.rsreu.library.model.books.Book;
import ru.rsreu.library.model.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OracleBookDAO implements BookDAO {

    private static final String SELECT_BOOK = ProjectResourcer.getInstance().getString("db.query.select.book");
    private static final String SELECT_BOOKS_BY_OWNER = ProjectResourcer.getInstance().getString("db.query.select.books.by.id");
    private static final String SELECT_ALL_BOOKS = ProjectResourcer.getInstance().getString("db.query.select.all.books");
    private static final String SELECT_RATE_BOOK = ProjectResourcer.getInstance().getString("db.query.select.rate.book");
    private static final String SELECT_TAKEN_BOOKS = ProjectResourcer.getInstance().getString("db.query.select.taken.books");
    private static final String UPDATE_BOOK = ProjectResourcer.getInstance().getString("db.query.update.book");
    private static final String UPDATE_RATE_BOOK = ProjectResourcer.getInstance().getString("db.query.update.rating.book");
    private static final String DELETE_BOOK = ProjectResourcer.getInstance().getString("db.query.delete.book");
    private static final String INSERT_RATE_BOOK = ProjectResourcer.getInstance().getString("db.query.insert.rate.book");
    private static final String INSERT_BOOK = ProjectResourcer.getInstance().getString("db.query.insert.book");

    @Override
    public ArrayList<Book> getBooksByOwner(User user) {
        return queryForBooks(SELECT_BOOKS_BY_OWNER, user.getId());
    }

    @Override
    public ArrayList<Book> getAvailableBooks() {
        return queryForBooks(SELECT_BOOKS_BY_OWNER, 0);
    }

    @Override
    public ArrayList<Book> getTakenBooks(User user) {
        ArrayList<Book> books = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TAKEN_BOOKS);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, user.getId());

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                books.add(giveBook(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return books;
    }

    @Override
    public ArrayList<Book> findAllBooks() {
        ArrayList<Book> books = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                books.add(giveBook(resultSet));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return books;
    }

    @Override
    public Book getBookById(int id) {
        Book book = null;

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                book = giveBook(resultSet);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return book;
    }

    @Override
    public Book getBookById(String id) {
        return getBookById(Integer.parseInt(id));
    }

    @Override
    public void addBook(Book book) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOK);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getOwnerId());
            preparedStatement.setString(3, book.getAuthor());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateBook(Book book) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getOwnerId());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setInt(4, book.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteBook(Book book) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK);
            preparedStatement.setInt(1, book.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void rateBook(User user, Book book, int rate) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RATE_BOOK);
            setFirstUserParameters(preparedStatement, user, book);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                preparedStatement = connection.prepareStatement(UPDATE_RATE_BOOK);

                setRateParameters(preparedStatement, rate, 1);
                setSecondUserParameters(preparedStatement, user, book);
            } else {
                preparedStatement = connection.prepareStatement(INSERT_RATE_BOOK);

                setFirstUserParameters(preparedStatement, user, book);
                setRateParameters(preparedStatement, rate, 3);
            }

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private ArrayList<Book> queryForBooks(String query, int id) {
        ArrayList<Book> books = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                books.add(giveBook(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return books;
    }

    private void setFirstUserParameters(PreparedStatement preparedStatement, User user, Book book) throws SQLException {
        setUserParameters(preparedStatement, user, book, 1, 2);
    }

    private void setSecondUserParameters(PreparedStatement preparedStatement, User user, Book book) throws SQLException {
        setUserParameters(preparedStatement, user, book, 2, 3);
    }

    private void setUserParameters(PreparedStatement preparedStatement, User user, Book book, int firstIndex, int lastIndex) throws SQLException {
        preparedStatement.setInt(firstIndex, user.getId());
        preparedStatement.setInt(lastIndex, book.getId());
    }

    private void setRateParameters(PreparedStatement preparedStatement, int rate, int index) throws SQLException {
        preparedStatement.setInt(index, rate);
    }

    private Book giveBook(ResultSet resultSet) throws SQLException {
        Book book = new Book();

        book.setId(resultSet.getInt("book_id"));
        book.setAuthor(resultSet.getString("author"));
        book.setOwnerId(resultSet.getInt("owner_id"));
        book.setTitle(resultSet.getString("title"));

        return book;
    }
}
