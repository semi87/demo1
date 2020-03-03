package board.servlets;

import board.enums.AdvertisementStatus;
import board.model.Advertisement;
import board.model.User;
import board.service.AdvertisementServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserAdvertisementServlet extends HttpServlet {
    private AdvertisementServiceImpl advertisementService;

    public UserAdvertisementServlet() {
        super();
        advertisementService = new AdvertisementServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        String initParam = getServletConfig().getInitParameter("advertisements_status");
        AdvertisementStatus advertisementStatus = null;
        try {
            List<Advertisement> advertisementsList;
            switch (initParam){
                case "active":{
                   advertisementStatus=AdvertisementStatus.ACTIVE;
                    break;
                }
                case "pending":{
                    advertisementStatus=AdvertisementStatus.NEW;
                    break;
                }
                case "expired":{
                    advertisementStatus=AdvertisementStatus.EXPIRED;
                    break;
                }  case "rejected":{
                    advertisementStatus=AdvertisementStatus.REJECTED;
                    break;
                }
                default:
                    throw new IllegalStateException("Unexpected value: " + initParam);
            }

            advertisementsList=advertisementService.getAdvertisementsByUserAndType(new User((Long)session.getAttribute("user_id")),advertisementStatus);

            request.setAttribute("advertisementsList", advertisementsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("user_menu", initParam);
        request.getRequestDispatcher("/user_advertisement.jsp")
                .forward(request, response);

    }

}