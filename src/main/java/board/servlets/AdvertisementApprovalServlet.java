package board.servlets;

import board.enums.AdvertisementStatus;
import board.model.Advertisement;
import board.model.User;
import board.service.AdvertisementServiceImpl;
import board.service.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class AdvertisementApprovalServlet extends HttpServlet {
    private AdvertisementServiceImpl advertisementService;
    private UserServiceImpl userService;
    public static final Logger log= Logger.getLogger(NewAdvertisementServlet.class);

    public AdvertisementApprovalServlet() {
        super();
        advertisementService = new AdvertisementServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String message="";
        Long advertisementId = Long.parseLong(request.getParameter("advertisementId"));
        AdvertisementStatus status = AdvertisementStatus.valueOf(request.getParameter("status"));
        String reason = (String)request.getParameter("reason");

        Advertisement advertisement = new Advertisement();
        advertisement.setId(advertisementId);
        advertisement.setStatus(status);
        try {
            if(advertisementService.updateStatusById(advertisement)){
            if(reason!=null){
                Long userId = Long.parseLong(request.getParameter("userId"));
                userService = new UserServiceImpl();
                User user  = userService.getUserById(userId);
                if(user!=null){
                    advertisementService.sendNotification(reason, user.getEmail());
                }
            }
            message="success";
            }else {
                message="error";
            }
        } catch (SQLException e) {
            log.error(e);
        }

        PrintWriter printer = response.getWriter();
        printer.print(message);
        printer.close();
    }




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        try {
            List<Advertisement> advertisementsList;
            advertisementsList=advertisementService.getAdvertisementsByType(AdvertisementStatus.NEW);
            request.setAttribute("advertisementsList", advertisementsList);
        } catch (Exception e) {
           log.error(e);
        }

        request.getRequestDispatcher("/approval_advertisement.jsp")
                .forward(request, response);

    }

}
