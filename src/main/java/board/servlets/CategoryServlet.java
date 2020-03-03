package board.servlets;

import board.model.Category;
import board.service.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CategoryServlet extends HttpServlet {
    private CategoryServiceImpl categoryService;

    public CategoryServlet() {
        super();
        categoryService = new CategoryServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String errors="";
        try {
            int categoryId = 0;
            String categoryTitle = request.getParameter("categoryTitle");
            String action = (String) request.getParameter("action");
            try {
                categoryId = Integer.parseInt(request.getParameter("categoryId"));
            } catch (Exception e) {

            }
            switch (action) {
                case "add": {
                    errors = categoryService.save(new Category(categoryTitle));
                    break;
                }
                case "edit": {
                    errors = categoryService.update(new Category(categoryId, categoryTitle));
                    break;
                }
                case "delete": {
                    errors = categoryService.delete(new Category(categoryId));
                    break;
                }
            }
        } catch (Exception e) {

        }

        PrintWriter printer = response.getWriter();
        printer.print(errors);
        printer.close();



    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            List<Category> categoryList;
            categoryList = categoryService.getAll();
            request.setAttribute("categoryList", categoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/category.jsp")
                .forward(request, response);

    }

}
