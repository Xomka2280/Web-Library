package ru.rsreu.library.oracle;

import com.prutzkow.resourcer.ProjectResourcer;
import ru.rsreu.library.dao.BookDAO;
import ru.rsreu.library.dao.DAOFactory;
import ru.rsreu.library.dao.NoticesDAO;
import ru.rsreu.library.dao.UserDAO;
import ru.rsreu.library.database.ConnectionPool;
import ru.rsreu.library.database.DBType;
import ru.rsreu.library.model.books.Book;
import ru.rsreu.library.model.books.Notice;
import ru.rsreu.library.model.users.User;
import ru.rsreu.library.utils.NumericHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OracleNoticesDAO implements NoticesDAO {

    private static final DAOFactory daoFactory;

    private static final String SELECT_NOTICE_BY_ID = ProjectResourcer.getInstance().getString("db.query.select.notice.by.id");
    private static final String SELECT_NOTICES_BY_OWNER = ProjectResourcer.getInstance().getString("db.query.select.notices.by.owner");
    private static final String INSERT_REQUEST_BOOK = ProjectResourcer.getInstance().getString("db.query.insert.notices");
    private static final String UPDATE_BOOK_NOTICE = ProjectResourcer.getInstance().getString("db.query.update.book.notice");
    private static final String DELETE_NOTICE = ProjectResourcer.getInstance().getString("db.query.delete.notice");

    static {
        daoFactory = DAOFactory.getInstance(DBType.ORACLE);
    }

    @Override
    public ArrayList<Notice> getNoticesByOwner(User user) {
        ArrayList<Notice> notices = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOTICES_BY_OWNER);
            preparedStatement.setInt(1, user.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                notices.add(createNotice(resultSet, user));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return notices;
    }

    @Override
    public Notice getNoticeById(int id) {
        Notice notice = null;

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOTICE_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                notice = createNotice(resultSet);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return notice;
    }

    @Override
    public Notice getNoticeById(String id) {
        return getNoticeById(Integer.parseInt(id));
    }

    @Override
    public void updateBookNotice(Book book, boolean available) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK_NOTICE);
            preparedStatement.setInt(1, NumericHelper.convertToInt(available));
            preparedStatement.setInt(2, book.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void createRequest(Notice notice) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REQUEST_BOOK);
            preparedStatement.setInt(1, notice.getUser().getId());
            preparedStatement.setInt(2, notice.getBook().getId());
            preparedStatement.setInt(3, NumericHelper.convertToInt(notice.isAvailable()));

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteNotice(Notice notice) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_NOTICE);
            preparedStatement.setInt(1, notice.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private Notice createNotice(ResultSet resultSet) throws SQLException {
        UserDAO userDAO = daoFactory.getUserDAO();
        User user = userDAO.getUserById(resultSet.getInt("reader_id"));

        return createNotice(resultSet, user);
    }

    private Notice createNotice(ResultSet resultSet, User user) throws SQLException {
        BookDAO bookDAO = daoFactory.getBookDAO();

        Notice notice = new Notice();

        notice.setId(resultSet.getInt("notice_id"));
        notice.setUser(user);
        notice.setBook(bookDAO.getBookById(resultSet.getInt("book_id")));
        notice.setAvailable(NumericHelper.convertToBool(resultSet.getInt("status")));

        return notice;
    }

}
