package by.bsuir.lab4.dao;

import by.bsuir.lab4.database.QueryPrepare;
import by.bsuir.lab4.entity.Room;
import by.bsuir.lab4.exception.DaoException;
import by.bsuir.lab4.exception.RepositoryException;
import by.bsuir.lab4.repository.RepositoryFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RoomDao {
    public static List<Room> findAllByOccupied(Boolean occupied) throws DaoException{
        List<Object> params = Collections.singletonList(occupied);
        String sql = QueryPrepare.makeSelectQuery(Room.OCCUPIED, occupied, Room.TABLE_NAME);
        try {
            return RepositoryFactory.getRoomRepository().queryAll(sql, params);
        } catch (RepositoryException e) {
            throw new DaoException(e.getMessage());
        }
    }
    public static Room findById(Integer id) throws DaoException{
        List<Object> params = Collections.singletonList(id);
        String sql = QueryPrepare.makeSelectQuery(Room.ID, id, Room.TABLE_NAME);
        try {
            Optional<Room> room = RepositoryFactory.getRoomRepository().query(sql, params);
            if(room.isPresent())
                return room.get();
            else
                return null;
        } catch (RepositoryException e) {
            throw new DaoException(e.getMessage());
        }
    }
    public static Room findByNumber(String roomNumber) throws DaoException{
        List<Object> params = Collections.singletonList(roomNumber);
        String sql = QueryPrepare.makeSelectQuery(Room.ROOM_NUMBER, roomNumber, Room.TABLE_NAME);
        try {
            Optional<Room> room = RepositoryFactory.getRoomRepository().query(sql, params);
            if(room.isPresent())
                return room.get();
            else
                return null;
        } catch (RepositoryException e) {
            throw new DaoException(e.getMessage());
        }
    }
    public static void Save(Room room) throws DaoException {
        try {
            RepositoryFactory.getRoomRepository().save(room);
        } catch (RepositoryException e) {
            throw new DaoException(e.getMessage());
        }
    }
}
