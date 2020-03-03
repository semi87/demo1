package board.servlets;

import board.service.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginServlet extends HttpServlet {
    private UserServiceImpl userService;

    public LoginServlet() {
        super();
        userService = new UserServiceImpl();
    }

    private static Logger log = Logger.getLogger(UserServiceImpl.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (userService.authorizationUser(request)) {
                response.sendRedirect(request.getContextPath() + "/");
            } else {
                request.getRequestDispatcher("/login.jsp")
                        .forward(request, response);
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp")
                .forward(request, response);
    }
}