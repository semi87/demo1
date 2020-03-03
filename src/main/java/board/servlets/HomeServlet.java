package board.servlets;

import board.model.Category;
import board.service.AdvertisementServiceImpl;
import board.service.CategoryServiceImpl;
import board.service.interfaces.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HomeServlet extends HttpServlet {
    private AdvertisementServiceImpl advertisementService;
    private CategoryService categoryService;

    public HomeServlet() {
        super();
        advertisementService = new AdvertisementServiceImpl();
        categoryService = new CategoryServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> categoriesList;
        categoriesList = categoryService.getAll();
        request.setAttribute("categoriesList", categoriesList);
        advertisementService.getAdvertisements(request);
        request.getRequestDispatcher("/home.jsp")
                .forward(request, response);

    }

}