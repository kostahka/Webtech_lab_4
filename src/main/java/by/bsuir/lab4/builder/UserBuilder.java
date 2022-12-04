package by.bsuir.lab4.builder;

import by.bsuir.lab4.entity.Role;
import by.bsuir.lab4.entity.User;
import by.bsuir.lab4.exception.RepositoryException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements IBuilder<User> {

    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";

    @Override
    public User build(ResultSet resultSet) throws RepositoryException {
        try {
            Integer id = resultSet.getInt(ID);
            String login = resultSet.getString(USERNAME);
            String password = resultSet.getString(PASSWORD);
            Role role = Role.valueOf(resultSet.getString(ROLE).toUpperCase());

            return new User(id, login, password, role);
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }
}
