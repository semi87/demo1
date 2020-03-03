package board.servlets;

import board.enums.Role;
import board.enums.UserStatus;
import board.model.User;
import board.service.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UsersServlet extends HttpServlet {
    private UserServiceImpl userService;
    public static final Logger logger = Logger.getLogger(UsersServlet.class);

    public UsersServlet() {
        super();
        userService = new UserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            UserStatus userStatus = UserStatus.valueOf(request.getParameter("userStatus"));
            Role userRole = Role.valueOf(request.getParameter("userRole"));
            long userId = 0;
            userId = Long.parseLong(request.getParameter("userId"));
            userService.updateUserByID(new User(userId, userStatus, userRole));

        } catch (Exception e) {
            logger.error("User servlet doPostexception",e);
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<User> userList;
            userList = userService.getAllUsers();
            request.setAttribute("userList", userList);
        } catch (Exception e) {
            logger.error("User servlet doGet exception",e);
        }

        request.getRequestDispatcher("/users.jsp")
                .forward(request, response);

    }

}
