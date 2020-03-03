package board.impl;

import board.dao.DBConnectionManager;
import board.dao.ExceptionHandler;
import board.dao.interfaces.CategoryDAO;
import board.model.Category;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl extends DBConnectionManager implements CategoryDAO {
    private final String GET_ALL_CATEGORIES = "SELECT * FROM categories";
    private final String GET_CATEGORY_BY_TITLE = "SELECT * FROM categories WHERE title=?";
    private final String SAVE_CATEGORY = "INSERT INTO categories (title)  VALUES(?)";
    private final String UPDATE_CATEGORY_BY_ID = "UPDATE categories SET title=? WHERE id=?";
    private final String DELETE_CATEGORY_BY_ID = "DELETE FROM  categories WHERE id=?";
    public static final Logger log = Logger.getLogger(UserDaoImpl.class);


    @Override
    public List<Category> getAllCategories() throws ExceptionHandler {
        List<Category> categoriesList = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(GET_ALL_CATEGORIES);

            while (resultSet.next()) {
                Category post = new Category();
                post.setId(resultSet.getInt("id"));
                post.setTitle(resultSet.getString("title"));

                categoriesList.add(post);
            }
        } catch (SQLException e) {
             log.error("Method getAllCategories(): Cannot read category\n", e);
            throw new ExceptionHandler("Cannot read users", e);
        } catch (ClassNotFoundException e) {
            log.error("Method getAllCategories(): ClassNotFoundException\n", e);
        }
        return categoriesList;
    }

    @Override
    public boolean save(Category category) throws ExceptionHandler {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_CATEGORY)
        ) {
            preparedStatement.setString(1, category.getTitle());
            preparedStatement.setString(1, category.getTitle());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            log.error("Method getUserByEmailAndPassword(): Cannot read users\n", e);
            throw new ExceptionHandler("Cannot read users", e);
        } catch (ClassNotFoundException e) {
            log.error("Method getUserByEmailAndPassword(): ClassNotFoundException\n", e);
        }
        return false;
    }

    @Override
    public boolean update(Category category) throws ExceptionHandler {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY_BY_ID)
        ) {
            preparedStatement.setString(1, category.getTitle());
            preparedStatement.setLong(2, category.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            log.error("Method getUserByEmailAndPassword(): Cannot read users\n", e);
            throw new ExceptionHandler("Cannot read users", e);
        } catch (ClassNotFoundException e) {
            log.error("Method getUserByEmailAndPassword(): ClassNotFoundException\n", e);
        }
        return false;
    }

    @Override
    public boolean delete(Category category) throws ExceptionHandler {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORY_BY_ID)
        ) {
            preparedStatement.setLong(1, category.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            log.error("Method getUserByEmailAndPassword(): Cannot read users\n", e);
            throw new ExceptionHandler("Cannot read users", e);
        } catch (ClassNotFoundException e) {
            log.error("Method getUserByEmailAndPassword(): ClassNotFoundException\n", e);
        }
        return false;
    }

    @Override
    public List<Category> getCategoryBy_Title(Category category) throws ExceptionHandler {
        List<Category> categoriesList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CATEGORY_BY_TITLE)) {

            preparedStatement.setString(1, category.getTitle());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Category cat = new Category();
                category.setId(resultSet.getInt("id"));
                category.setTitle(resultSet.getString("title"));
                categoriesList.add(cat);
            }
        } catch (SQLException e) {
             log.error("Method getAllCategories(): Cannot read category\n", e);
            throw new ExceptionHandler("Cannot read users", e);
        } catch (ClassNotFoundException e) {
            log.error("Method getAllCategories(): ClassNotFoundException\n", e);
        }
        return categoriesList;
    }
}
