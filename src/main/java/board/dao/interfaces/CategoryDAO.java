package board.dao.interfaces;

import board.dao.ExceptionHandler;
import board.model.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> getAllCategories() throws ExceptionHandler;
    boolean save(Category category) throws ExceptionHandler;
    boolean update(Category category) throws ExceptionHandler;
    boolean delete(Category category) throws ExceptionHandler;
    List<Category> getCategoryBy_Title(Category category) throws ExceptionHandler;

}