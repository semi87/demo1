package board.servlets;

import board.impl.UserDaoImpl;
import board.model.Favorit;
import board.service.AdvertisementServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class FavoriteServlet extends HttpServlet {
    private AdvertisementServiceImpl advertisementService;
    public static final Logger log = Logger.getLogger(UserDaoImpl.class);

    public FavoriteServlet() {
        super();
        advertisementService = new AdvertisementServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        advertisementService.getFavoridAdvertisements(request);

        request.setAttribute("user_menu", "favorite");
        request.getRequestDispatcher("/favorites.jsp")
                .forward(request, response);

    }


    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        long advertisementId =0;
        long userId =0;
        String favorite_action = getServletConfig().getInitParameter("favorite_action");
        try {
            userId = (Long) session.getAttribute("user_id");
            advertisementId = Long.parseLong(request.getParameter("advertisementId"));
        }catch ( Exception e){
            log.error(e);
        }
        if (userId != 0 && advertisementId != 0) {
            if (favorite_action.equals("add")) {
                try {
                    advertisementService.saveFavorite(new Favorit(advertisementId, userId));
                } catch (Exception e) {
                    log.error(e);
                }
            } else if (favorite_action.equals("delete")) {
                try {
                    advertisementService.deleteFavorite(new Favorit(advertisementId, userId));
                } catch (Exception e) {
                    log.error(e);
                }
            }
        }

    }
}