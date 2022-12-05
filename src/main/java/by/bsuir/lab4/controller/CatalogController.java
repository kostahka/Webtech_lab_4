package by.bsuir.lab4.controller;

import by.bsuir.lab4.dao.RoomDao;
import by.bsuir.lab4.entity.Role;
import by.bsuir.lab4.entity.Room;
import by.bsuir.lab4.exception.DaoException;
import by.bsuir.lab4.exception.ServiceException;
import by.bsuir.lab4.service.RoomService;
import by.bsuir.lab4.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "catalogController", urlPatterns = "/catalog")
public class CatalogController extends HttpServlet {
    private static final String ROOM_LIST = "roomList";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Room> roomList = RoomDao.findAll();
            String roomId = req.getParameter("roomId");
            if(roomId != null){
                Integer id =Integer.parseInt(roomId);
                try {
                    RoomService.OccupyRoom(id);
                } catch (ServiceException e) {
                    throw new RuntimeException(e);
                }
                for (Room room:roomList) {
                    if(room.getId() == id){
                        room.setOccupied(true);
                        break;
                    }
                }
            }
            req.setAttribute(ROOM_LIST, roomList);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pages/roomCatalog.jsp");
        requestDispatcher.forward(req, resp);
    }
}
