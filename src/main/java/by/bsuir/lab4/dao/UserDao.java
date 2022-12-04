package by.bsuir.lab4.dao;

import by.bsuir.lab4.database.QueryPrepare;
import by.bsuir.lab4.entity.Room;
import by.bsuir.lab4.entity.User;
import by.bsuir.lab4.exception.DaoException;
import by.bsuir.lab4.exception.RepositoryException;
import by.bsuir.lab4.repository.RepositoryFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserDao {
    public static User findByUsername(String username) throws DaoException {
        List<Object> params = Collections.singletonList(username);
        String sql = QueryPrepare.makeSelectQuery(User.USERNAME, username, User.TABLE_NAME);
        try {
            Optional<User> user = RepositoryFactory.getUserRepository().query(sql, params);
            if(user.isPresent())
                return user.get();
            else
                return null;
        } catch (RepositoryException e) {
            throw new DaoException(e.getMessage());
        }
    }
    public static void Save(User user) throws DaoException {
        try {
            RepositoryFactory.getUserRepository().save(user);
        } catch (RepositoryException e) {
            throw new DaoException(e.getMessage());
        }
    }
}
