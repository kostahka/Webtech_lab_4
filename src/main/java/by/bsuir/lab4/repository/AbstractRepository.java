package by.bsuir.lab4.repository;

import by.bsuir.lab4.builder.IBuilder;
import by.bsuir.lab4.database.DatabaseQuery;
import by.bsuir.lab4.database.QueryPrepare;
import by.bsuir.lab4.entity.Entity;
import by.bsuir.lab4.exception.RepositoryException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public abstract class AbstractRepository<T extends Entity> implements IRepository<T>{

    public AbstractRepository() {}

    public List<T> executeQuery(String sql, IBuilder<T> builder, List<Object> params) throws RepositoryException {
        List<T> objects = new ArrayList<>();
        try {
            ResultSet resultSet = DatabaseQuery.getInstance().PutExecuteQuery(sql, params);

            while (resultSet.next()) {
                T item = builder.build(resultSet);
                objects.add(item);
            }
        } catch (SQLException ex) {
            throw new RepositoryException(ex.getMessage());
        }
        return objects;
    }

    public Optional<T> executeQueryForSingleResult(String query, IBuilder<T> builder, List<Object> params) throws RepositoryException {
        List<T> items = executeQuery(query, builder, params);

        return items.size() == 1 ?
                Optional.of(items.get(0)) :
                Optional.empty();
    }

    public void save(T item) throws RepositoryException {
        String sql;
        if (item.getId() != null) {
            sql = QueryPrepare.makeUpdateQuery(getFields(item), getTableName());
            DatabaseQuery.getInstance().PutExecuteUpdate(sql,
                    Arrays.asList(getFields(item).values().toArray()));
        } else {
            sql = QueryPrepare.makeInsertQuery(getFields(item), getTableName());
            Map<String, Object> itemFields = getFields(item);
            if(itemFields.containsKey("id"))
                itemFields.remove("id");

            DatabaseQuery.getInstance().PutExecuteUpdate(sql,
                    Arrays.asList(itemFields.values().toArray()));
        }


    }
}
