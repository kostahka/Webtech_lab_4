package by.bsuir.lab4.controller;

import by.bsuir.lab4.dao.UserDao;
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

@WebServlet(name = "signUpController", value = "/signUp")
public class SignUpController extends HttpServlet {
    private static final String ROLE = "role";
    private static final String SIGN_UP_ERROR = "signUpError";
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
                if(UserService.SignUp(username, password)){
                    req.getSession().setAttribute(ROLE, Role.USER);
                    resp.sendRedirect("catalog");
                    return;
                }else{
                    req.setAttribute(SIGN_UP_ERROR, username);
                }
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }

        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pages/signup.jsp");
        requestDispatcher.forward(req, resp);
    }
}
