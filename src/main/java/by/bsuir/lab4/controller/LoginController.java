package by.bsuir.lab4.controller;

import by.bsuir.lab4.entity.Role;
import by.bsuir.lab4.exception.ServiceException;
import by.bsuir.lab4.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loginController", value = "/login")
public class LoginController extends HttpServlet {
    private static final String ROLE = "role";
    private static final String LOGIN_ERROR = "loginError";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(username != null && password != null){
            try {
                Role role = UserService.LogIn(username, password);
                req.getSession().setAttribute(ROLE, role);
                if(role.equals(Role.USER)){
                    resp.sendRedirect("catalog");
                    return;
                } else if (role.equals(Role.ADMIN)) {
                    resp.sendRedirect("catalog/edit");
                    return;
                } else if (role.equals(Role.UnAUTH)) {
                    req.setAttribute(LOGIN_ERROR, true);
                }
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }

        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pages/login.jsp");
        requestDispatcher.forward(req, resp);
    }
}
