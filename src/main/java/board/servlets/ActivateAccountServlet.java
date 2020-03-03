package board.servlets;

import board.service.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ActivateAccountServlet extends HttpServlet {
    private UserServiceImpl userService;
    public static final Logger log = Logger.getLogger(ActivateAccountServlet.class);

    public ActivateAccountServlet() {
        super();
        userService = new UserServiceImpl();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        try {
            userService.activateAccount(request);
        } catch (Exception e) {
            log.error(e);
        }


        request.getRequestDispatcher("/activate_account.jsp")
                .forward(request, response);

    }
}