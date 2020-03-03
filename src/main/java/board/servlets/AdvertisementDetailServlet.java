package board.servlets;

import board.model.Advertisement;
import board.service.AdvertisementServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class AdvertisementDetailServlet extends HttpServlet {
    private AdvertisementServiceImpl advertisementService;

    public AdvertisementDetailServlet() {
        super();
        advertisementService = new AdvertisementServiceImpl();
    }

    static Logger log = Logger.getLogger(String.valueOf(AdvertisementDetailServlet.class));
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        URL url = new URL(request.getRequestURL().toString());
        Advertisement advertisementDetails = null;
        String urlPath = url.getPath();
        long post_id= Long.parseLong(urlPath.substring(urlPath.lastIndexOf("/") + 1, urlPath.length()));
        if(post_id!=0){
            try {
                advertisementDetails= advertisementService.getAdvertisementsById(post_id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("advertisementDetails", advertisementDetails);
        request.getRequestDispatcher("/advertisement_detail.jsp")
                .forward(request, response);

    }

}