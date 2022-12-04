package by.bsuir.lab4.repository;

import by.bsuir.lab4.exception.RepositoryException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IRepository<T> {
    Optional<T> query(String sql, List<Object> params) throws RepositoryException;

    List<T> queryAll(String sql, List<Object> params) throws RepositoryException;

    void save(T item) throws RepositoryException;

    String getTableName();

    Map<String, Object> getFields(T item);
}
