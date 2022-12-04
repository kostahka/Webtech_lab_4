package by.bsuir.lab4.repository.impl;

import by.bsuir.lab4.builder.IBuilder;
import by.bsuir.lab4.builder.UserBuilder;
import by.bsuir.lab4.entity.User;
import by.bsuir.lab4.exception.RepositoryException;
import by.bsuir.lab4.repository.AbstractRepository;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserRepository extends AbstractRepository<User> {


    @Override
    public Map<String, Object> getFields(User item) {
        Map<String, Object> values = new LinkedHashMap<>();
        values.put(User.USERNAME, item.getUsername());
        values.put(User.PASSWORD, item.getPassword());
        values.put(User.ROLE, item.getRole());
        values.put(User.ID, item.getId());
        return values;
    }

    @Override
    public String getTableName() {
        return User.TABLE_NAME;
    }

    @Override
    public Optional<User> query(String sql, List<Object> params) throws RepositoryException {
        IBuilder<User> builder = new UserBuilder();
        return executeQueryForSingleResult(sql, builder, params);
    }

    @Override
    public List<User> queryAll(String sql, List<Object> params) throws RepositoryException {
        IBuilder<User> builder = new UserBuilder();
        return executeQuery(sql, builder, params);
    }
}
