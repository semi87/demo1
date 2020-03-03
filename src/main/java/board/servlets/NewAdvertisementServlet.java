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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class NewAdvertisementServlet extends HttpServlet {
    private AdvertisementServiceImpl advertisementServiceImpl;
    private CategoryService categoryService;
    public NewAdvertisementServlet() {
        super();
        advertisementServiceImpl = new AdvertisementServiceImpl();
        categoryService = new CategoryServiceImpl();
    }

    public static final Logger log= Logger.getLogger(NewAdvertisementServlet.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();

        String title = request.getParameter("title");
        String short_description = request.getParameter("short_description");
        String description = request.getParameter("description");
        int category_id = Integer.parseInt(request.getParameter("category"));


        Advertisement advertisement = new Advertisement();
        advertisement.setTitle(title);
        advertisement.setShort_description(short_description);
        advertisement.setDescription(description);
        advertisement.setCategory(new Category(category_id));
        advertisement.setUser(new User((Long)session.getAttribute("user_id")));
        advertisement.setStatus(AdvertisementStatus.NEW);
        String expiration_date = (String) request.getParameter("expiration_date");
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = (Date) formatter.parse(expiration_date);
            Date parsedDate = new java.sql.Date(myDate.getTime());
            advertisement.setExpire_date(parsedDate);
        } catch(Exception e) {
            log.error(e);
        }
        String message ="";
        try {
            if(advertisementServiceImpl.save(advertisement)){
                message = "Success";
            }else{
                message = "Error";
            }
        } catch (Exception e) {
            log.error(e);
            message = "Error";
        }
        request.setAttribute("message", message);
        List<Category> categoriesList = categoryService.getAll();
        request.setAttribute("categoriesList", categoriesList);
        request.getRequestDispatcher("/new_advertisement.jsp")
                .forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        List<Category> categoriesList = categoryService.getAll();
        request.setAttribute("categoriesList", categoriesList);
        request.getRequestDispatcher("/new_advertisement.jsp")
                .forward(request, response);
    }
}