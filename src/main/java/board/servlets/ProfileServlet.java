package board.servlets;
import board.model.User;
import board.service.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class ProfileServlet extends HttpServlet {
    private UserServiceImpl userService;
    private static Logger log = Logger.getLogger(UserServiceImpl.class.getName());

    public ProfileServlet() {
        super();
        userService = new UserServiceImpl();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Long user_id = (Long) session.getAttribute("user_id");
        User userDetails = null;
        try {
            userDetails = userService.getUserById(user_id);
        } catch (Exception e) {
            log.error(e);
        }
        request.setAttribute("user_menu", "profile");

        request.setAttribute("userDetails", userDetails);
        request.getRequestDispatcher("/profile.jsp")
                .forward(request, response);

    }
}