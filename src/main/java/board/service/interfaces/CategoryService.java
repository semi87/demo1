package board.service.interfaces;

import board.dao.ExceptionHandler;
import board.model.Category;

import java.util.List;

public interface CategoryService {
        List<Category> getAll();
        String save(Category category) throws ExceptionHandler;
        String update(Category category) throws ExceptionHandler;
        String delete(Category category) throws ExceptionHandler;
}
