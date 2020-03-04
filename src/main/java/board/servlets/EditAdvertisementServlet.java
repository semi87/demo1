package board.servlets;

import board.enums.AdvertisementStatus;
import board.model.Advertisement;
import board.model.Category;
import board.model.User;
import board.service.AdvertisementServiceImpl;
import board.service.CategoryServiceImpl;
import board.service.interfaces.CategoryService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class EditAdvertisementServlet extends HttpServlet {
    private final AdvertisementServiceImpl advertisementServiceImpl;
    private final CategoryService categoryService;

    public EditAdvertisementServlet() {
        super();
        advertisementServiceImpl = new AdvertisementServiceImpl();
        categoryService = new CategoryServiceImpl();
    }

    public static final Logger log = Logger.getLogger(EditAdvertisementServlet.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String message = "";
        try {
            String title = request.getParameter("title");
            String short_description = request.getParameter("short_description");
            String description = request.getParameter("description");
            int categoryId = Integer.parseInt(request.getParameter("category"));
            long advertisementId = Long.parseLong(request.getParameter("advertisementId"));


            Advertisement advertisement = new Advertisement();
            advertisement.setId(advertisementId);
            advertisement.setTitle(title);
            advertisement.setShort_description(short_description);
            advertisement.setDescription(description);
            advertisement.setCategory(new Category(categoryId));
            advertisement.setUser(new User((Long) session.getAttribute("user_id")));
            advertisement.setStatus(AdvertisementStatus.NEW);
            String expiration_date = (String) request.getParameter("expiration_date");

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = (Date) formatter.parse(expiration_date);
            Date parsedDate = new java.sql.Date(myDate.getTime());
            advertisement.setExpire_date(parsedDate);
            if (advertisementServiceImpl.updateById(advertisement)) {
                message = "Success";
            } else {
                message = "Error";
            }
        } catch (Exception e) {
            log.error(e);
            message = "Error";
        }
        request.setAttribute("message", message);
        List<Category> categoriesList = categoryService.getAll();
        request.setAttribute("categoriesList", categoriesList);
        request.getRequestDispatcher("/edit_advertisement.jsp")
                .forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            long userId = (Long) session.getAttribute("user_id");
            List<Category> categoriesList = categoryService.getAll();
            request.setAttribute("categoriesList", categoriesList);
            long advertisementId = Long.parseLong(request.getParameter("advertisementId"));
            if (advertisementId != 0) {

                Advertisement result = advertisementServiceImpl.getAdvertisementsById_And_UserId(advertisementId, userId);
                if (result.getUser().getId() == userId) {
                    request.setAttribute("advertisement", result);
                    request.getRequestDispatcher("/edit_advertisement.jsp")
                            .forward(request, response);
                } else {
                    request.getRequestDispatcher("/404.jsp")
                            .forward(request, response);
                }
            }
        } catch (SQLException e) {
            log.error(e);
        } catch (NullPointerException e) {
            log.error(e);
            request.getRequestDispatcher("/404.jsp")
                    .forward(request, response);
        }
    }
}