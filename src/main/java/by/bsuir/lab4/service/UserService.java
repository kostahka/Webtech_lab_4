package by.bsuir.lab4.service;

import by.bsuir.lab4.dao.UserDao;
import by.bsuir.lab4.entity.Role;
import by.bsuir.lab4.entity.User;
import by.bsuir.lab4.exception.DaoException;
import by.bsuir.lab4.exception.ServiceException;

public class UserService {
    public static Role LogIn(String login, String password) throws ServiceException {
        try {
            User user = UserDao.findByUsername(login);
            if(user != null && user.getPassword().equals(password)){
                return user.getRole();
            }else{
                return Role.UnAUTH;
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public static Boolean SignUp(String login, String password) throws ServiceException {
        try {
            User user = UserDao.findByUsername(login);
            if(user == null){
                UserDao.Save(new User(null, login, password, Role.USER));
                return true;
            }else{
                return false;
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
