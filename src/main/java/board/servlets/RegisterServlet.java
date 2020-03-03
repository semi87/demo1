package board.servlets;

import board.service.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RegisterServlet extends HttpServlet {
    private UserServiceImpl userService;
    public static final Logger log = org.apache.log4j.Logger.getLogger(UsersServlet.class);

    public RegisterServlet() {
        super();
        userService = new UserServiceImpl();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*if(email == null || email.trim().equals("")){
            errorMsg = "Email ID can't be null or empty.";
        }
        if(password == null || password.trim().equals("")){
            errorMsg = "Password can't be null or empty.";
        }
        if(!password.equals(confirm_password)){
            errorMsg = "Conform password is different";
        }*/

       /* */

        try {
            userService.save(request);
        } catch (Exception e) {
            log.error(e);
        }


        //request.setAttribute("errorMsg", errorMsg);

        request.getRequestDispatcher("/registration.jsp")
                .forward(request, response);



    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        request.getRequestDispatcher("/registration.jsp")
                .forward(request, response);
    }
}