package by.bsuir.lab4.builder;

import by.bsuir.lab4.entity.Entity;
import by.bsuir.lab4.exception.RepositoryException;

import java.sql.ResultSet;

public interface IBuilder<T extends Entity> {
    T build(ResultSet resultSet) throws RepositoryException;
}
