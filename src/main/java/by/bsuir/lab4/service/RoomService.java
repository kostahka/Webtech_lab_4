package by.bsuir.lab4.service;

import by.bsuir.lab4.dao.RoomDao;
import by.bsuir.lab4.entity.Role;
import by.bsuir.lab4.entity.Room;
import by.bsuir.lab4.exception.DaoException;
import by.bsuir.lab4.exception.ServiceException;

public class RoomService {
    public static Boolean addRoom(String roomNumber) throws ServiceException {
        try {
            Room room = RoomDao.findByNumber(roomNumber);
            if(room == null){
                RoomDao.Save(new Room(null, roomNumber, false));
                return true;
            }else{
                return false;
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public static Boolean DeoccupyRoom(Integer id) throws ServiceException{
        try {
            Room room = RoomDao.findById(id);
            if(room != null){
                    room.setOccupied(false);
                    RoomDao.Save(room);
                return true;
            }else{
                return false;
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public static Boolean OccupyRoom(Integer id) throws ServiceException{
        try {
            Room room = RoomDao.findById(id);
            if(room != null){
                room.setOccupied(true);
                RoomDao.Save(room);
                return true;
            }else{
                return false;
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
