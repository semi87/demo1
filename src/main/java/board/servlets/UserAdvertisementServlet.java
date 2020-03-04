package board.servlets;

import board.enums.AdvertisementStatus;
import board.model.Advertisement;
import board.model.User;
import board.service.AdvertisementServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserAdvertisementServlet extends HttpServlet {
    private  final AdvertisementServiceImpl advertisementService;
    public static final Logger log = Logger.getLogger(UserAdvertisementServlet.class);

    public UserAdvertisementServlet() {
        super();
        advertisementService = new AdvertisementServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String message = "";
        HttpSession session = request.getSession();
        try {
            long userId = (Long) session.getAttribute("user_id");
            Long advertisementId = Long.parseLong(request.getParameter("advertisementId"));
            Advertisement advertisement = new Advertisement();
            advertisement.setId(advertisementId);
            advertisement.setStatus(AdvertisementStatus.EXPIRED);
            advertisement.setUser(new User(userId));

            if (advertisementService.updateStatusByIdAndUserId(advertisement)) {
                message = "success";
            } else {
                message = "error";
            }
        } catch (Exception e) {
            log.error(e);
        }

        PrintWriter printer = response.getWriter();
        printer.print(message);
        printer.close();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String initParam = getServletConfig().getInitParameter("advertisements_status");
        AdvertisementStatus advertisementStatus = null;
        try {
            List<Advertisement> advertisementsList;
            switch (initParam) {
                case "active": {
                    advertisementStatus = AdvertisementStatus.ACTIVE;
                    break;
                }
                case "pending": {
                    advertisementStatus = AdvertisementStatus.NEW;
                    break;
                }
                case "expired": {
                    advertisementStatus = AdvertisementStatus.EXPIRED;
                    break;
                }
                case "rejected": {
                    advertisementStatus = AdvertisementStatus.REJECTED;
                    break;
                }
                default:
                    throw new IllegalStateException("Unexpected value: " + initParam);
            }

            advertisementsList = advertisementService.getAdvertisementsByUserAndType(new User((Long) session.getAttribute("user_id")), advertisementStatus);

            request.setAttribute("advertisementsList", advertisementsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("user_menu", initParam);
        request.getRequestDispatcher("/user_advertisement.jsp")
                .forward(request, response);

    }

}