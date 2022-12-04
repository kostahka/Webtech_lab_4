package by.bsuir.lab4.repository.impl;

import by.bsuir.lab4.builder.RoomBuilder;
import by.bsuir.lab4.entity.Room;
import by.bsuir.lab4.exception.RepositoryException;
import by.bsuir.lab4.repository.AbstractRepository;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RoomRepository extends AbstractRepository<Room> {
    @Override
    public Map<String, Object> getFields(Room item) {
        Map<String, Object> values = new LinkedHashMap<>();
        values.put(Room.ROOM_NUMBER, item.getRoomNumber());
        values.put(Room.OCCUPIED, item.getOccupied());
        values.put(Room.ID, item.getId());

        return values;
    }

    @Override
    public String getTableName() {
        return Room.TABLE_NAME;
    }

    @Override
    public Optional<Room> query(String sql, List<Object> params) throws RepositoryException {
        return executeQueryForSingleResult(sql, new RoomBuilder(), params);
    }

    @Override
    public List<Room> queryAll(String sql, List<Object> params) throws RepositoryException {
        return executeQuery(sql, new RoomBuilder(), params);
    }
}
