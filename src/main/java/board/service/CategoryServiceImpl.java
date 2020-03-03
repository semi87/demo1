package board.service;
import board.dao.ExceptionHandler;
import board.impl.CategoryDaoImpl;
import board.model.Category;
import board.service.interfaces.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDaoImpl categoryDao;

    public CategoryServiceImpl() {
        super();
        categoryDao = new CategoryDaoImpl();
    }

    @Override
    public List<Category> getAll() {
        try {
            return categoryDao.getAllCategories();
        } catch (ExceptionHandler exceptionHandler) {
            exceptionHandler.printStackTrace();
        }
        return null;
    }

    @Override
    public String save(Category category) throws ExceptionHandler {
        String messages = "ok";
        if(categoryDao.getCategoryBy_Title(category).isEmpty()){
            if(!categoryDao.save(category)){
                messages="Error";
            }
        }else{
            messages="Please change title";
        }
        return messages;
    }

    @Override
    public String update(Category category) throws ExceptionHandler {
         String messages = "ok";
        if(categoryDao.getCategoryBy_Title(category).isEmpty()){
            if(!categoryDao.update(category)){
                messages="Error";
            }
        }else{
            messages="Please change title";
        }
        return messages;
    }

    @Override
    public String delete(Category category) throws ExceptionHandler {
        String messages = "ok";
        if(!categoryDao.delete(category)){
            messages="Error";
        }
        return messages;
    }

}

