package board.impl;

import board.dao.DBConnectionManager;
import board.dao.interfaces.AdvertisementDAO;
import board.enums.AdvertisementStatus;
import board.enums.UserStatus;
import board.model.Advertisement;
import board.model.Category;
import board.model.Favorit;
import board.model.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdvertisementDaoImpl extends DBConnectionManager implements AdvertisementDAO {
    private Connection connection;
    private final String SAVE_SQL ="INSERT INTO advertisements " +
            "(title,short_description, description, category_id, user_id, status, expiration_date ) VALUES(?,?,?,?,?,?,?)";
    private final static String GET_ACTIVE_ADVERTISEMENTS ="SELECT advertisements.id,advertisements.title, short_description,created_date,c2.title as category_title FROM advertisements  JOIN categories c2 on advertisements.category_id = c2.id WHERE status=? ORDER BY advertisements.created_date DESC LIMIT ? offset ? ";
    private final static String FILTER_BY_TEXT_AND_CATEGORY_ADVERTISEMENTS ="SELECT advertisements.id,advertisements.title, short_description,created_date,c2.title as category_title FROM advertisements  JOIN categories c2 on advertisements.category_id = c2.id WHERE status=? AND (advertisements.title LIKE ? OR short_description LIKE ? OR description LIKE ?) AND advertisements.category_id=? LIMIT ? offset ?";
    private final static String FILTER_BY_TEXT_ADVERTISEMENTS ="SELECT advertisements.id,advertisements.title, short_description,created_date,c2.title as category_title FROM advertisements  JOIN categories c2 on advertisements.category_id = c2.id WHERE status=? AND (advertisements.title LIKE ? OR short_description LIKE ? OR description LIKE ?) LIMIT ? offset ?";
    private final static String FILTER_BY_CATEGORY_ADVERTISEMENTS ="SELECT advertisements.id,advertisements.title, short_description,created_date,c2.title as category_title FROM advertisements  JOIN categories c2 on advertisements.category_id = c2.id WHERE status=? AND advertisements.category_id=? LIMIT ? offset ?";
    private final String GET_ACTIVE_ADVERTISEMENTS_COUNT ="SELECT COUNT(id) FROM advertisements WHERE status=?";
    private final String GET_ACTIVE_ADVERTISEMENTS_COUNT_FILTERED_BY_GUERY ="SELECT COUNT(advertisements.id) FROM advertisements JOIN categories c2 on advertisements.category_id = c2.id WHERE status=? AND (advertisements.title LIKE ? OR short_description LIKE ? OR description LIKE ?)";
    private final String GET_ACTIVE_ADVERTISEMENTS_COUNT_FILTERED_BY_CATEGORY ="SELECT COUNT(advertisements.id) FROM advertisements JOIN categories c2 on advertisements.category_id = c2.id WHERE status=? AND advertisements.category_id=?";
    private final String GET_ACTIVE_ADVERTISEMENTS_COUNT_FILTERED_BY_GUERY_AND_CATEGORY ="SELECT COUNT(advertisements.id) FROM advertisements JOIN categories c2 on advertisements.category_id = c2.id WHERE status=? AND (advertisements.title LIKE ? OR short_description LIKE ? OR description LIKE ?) AND advertisements.category_id=?";
    private final String GET_ADVERTISEMENT_BY_ID ="SELECT advertisements.id, advertisements.title, description, created_date, c2.title as category_title, c3.name as user_name, c3.phone as user_phone FROM advertisements LEFT JOIN categories c2 on advertisements.category_id = c2.id LEFT JOIN users c3 on user_id = c3.id WHERE advertisements.status=? and c3.status=? and advertisements.id=? LIMIT 1";
    private final String GET_ADVERTISEMENT_BY_USER_AND_TYPE ="SELECT advertisements.id, advertisements.title, short_description, created_date, c2.title as category_title, c3.name as user_name, c3.id as user_id, description FROM advertisements  LEFT JOIN categories c2 on advertisements.category_id = c2.id LEFT JOIN users c3 on advertisements.user_id = c3.id WHERE advertisements.status=? and c3.id=?";
    private final String GET_ADVERTISEMENT_BY_TYPE ="SELECT advertisements.id, advertisements.title, short_description, created_date, c2.title as category_title, c3.name as user_name, c3.id as user_id, description FROM advertisements  LEFT JOIN categories c2 on advertisements.category_id = c2.id LEFT JOIN users c3 on advertisements.user_id = c3.id WHERE advertisements.status=?";
    private static final String UPDATE_STATUS_BY_ID = "UPDATE advertisements SET status =? WHERE id=?";
    private static final String SAVE_FAVORIT_BY_USER = "INSERT INTO favorites(advertisement_id,user_id) VALUES(?,?)";
    private static final String DELETE_FAVORIT_BY_ADVERTISEMENT_ID_AND_USER_ID = "DELETE FROM favorites  WHERE advertisement_id=? AND user_id=?";
    private final static String GET_FAVORITE_ADVERTISEMENTS ="SELECT c1.id, c1.title, short_description, created_date, c2.title as category_title, c3.name as user_name FROM favorites LEFT JOIN advertisements c1 on favorites.advertisement_id = c1.id LEFT JOIN categories c2 on c1.category_id = c2.id LEFT JOIN users c3 on c1.user_id = c3.id WHERE c1.status=? and favorites.user_id = ?";
    public static final Logger log = Logger.getLogger(UserDaoImpl.class);


    @Override
    public void save(Advertisement advertisement){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL)) {
            preparedStatement.setString(1, advertisement.getTitle());
            preparedStatement.setString(2, advertisement.getShort_description());
            preparedStatement.setString(3, advertisement.getDescription());
            preparedStatement.setLong(4, advertisement.getCategory().getId());
            preparedStatement.setLong(5, advertisement.getUser().getId());
            preparedStatement.setInt(6, advertisement.getStatus().ordinal());
            preparedStatement.setDate(7, (Date) advertisement.getExpire_date());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Method save advertisement SQLException \n", e);
        }
        catch (ClassNotFoundException e) {
            log.error("Method save advertisement(): ClassNotFoundException\n", e);
        }
    }

    @Override
    public boolean saveFavorite(Favorit favorit) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_FAVORIT_BY_USER)){
            preparedStatement.setLong(1, favorit.getAdvertisement_id());
            preparedStatement.setLong(2, favorit.getUser_id());
            return preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e);
        }
        return false;
    }

    @Override
    public boolean deleteFavorite(Favorit favorit) {
        try(Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FAVORIT_BY_ADVERTISEMENT_ID_AND_USER_ID)){
            preparedStatement.setLong(1, favorit.getAdvertisement_id());
            preparedStatement.setLong(2, favorit.getUser_id());
            return preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e);
        }
        return false;
    }

    @Override
    public List<Advertisement> getAdvertisements(int currentPage, int recordsPerPage){
        List<Advertisement> advertisementList = new ArrayList<>();
        int start = currentPage * recordsPerPage - recordsPerPage;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ACTIVE_ADVERTISEMENTS)){
            preparedStatement.setInt(1, AdvertisementStatus.ACTIVE.ordinal());
            preparedStatement.setInt(2, recordsPerPage);
            preparedStatement.setInt(3, start);
            advertisementList = getAdvertisementQuery(preparedStatement);
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e);
        }
        return advertisementList;
    }

    @Override
    public List<Advertisement> getFavoridAdvertisements(User user){
        List<Advertisement> advertisementList = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_FAVORITE_ADVERTISEMENTS)){
            preparedStatement.setInt(1, AdvertisementStatus.ACTIVE.ordinal());
            preparedStatement.setLong(2, user.getId());
            advertisementList = getAdvertisementQuery(preparedStatement);
        } catch (SQLException | ClassNotFoundException e) {
          log.error(e);
        }
        return advertisementList;
    }

    @Override
    public List<Advertisement> getAdvertisements(int currentPage, int recordsPerPage, String query){
        List<Advertisement> advertisementList = new ArrayList<>();
        int start = currentPage * recordsPerPage - recordsPerPage;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FILTER_BY_TEXT_ADVERTISEMENTS)){
            preparedStatement.setInt(1, AdvertisementStatus.ACTIVE.ordinal());
            preparedStatement.setString(2, "%"+ query + "%");
            preparedStatement.setString(3, "%"+ query + "%");
            preparedStatement.setString(4, "%"+ query + "%");
            preparedStatement.setInt(5, recordsPerPage);
            preparedStatement.setInt(6, start);
            advertisementList = getAdvertisementQuery(preparedStatement);
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e);
        }
        return advertisementList;
    }

    @Override
    public List<Advertisement> getAdvertisements(int currentPage, int recordsPerPage, String query,  int categoryId){
        List<Advertisement> advertisementList = new ArrayList<>();
        int start = currentPage * recordsPerPage - recordsPerPage;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FILTER_BY_TEXT_AND_CATEGORY_ADVERTISEMENTS)){
            preparedStatement.setInt(1, AdvertisementStatus.ACTIVE.ordinal());
            preparedStatement.setString(2, "%"+ query + "%");
            preparedStatement.setString(3, "%"+ query + "%");
            preparedStatement.setString(4, "%"+ query + "%");
            preparedStatement.setInt(5, categoryId);
            preparedStatement.setInt(6, recordsPerPage);
            preparedStatement.setInt(7, start);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Advertisement advertisement = new Advertisement();
                    advertisement.setId(resultSet.getLong("id"));
                    advertisement.setTitle(resultSet.getString("title"));
                    advertisement.setShort_description(resultSet.getString("short_description"));
                    advertisement.setCreated_date(resultSet.getDate("created_date"));
                    advertisement.setCategory(new Category(resultSet.getString("category_title")));
                    advertisement.setUser(new User(resultSet.getString("user_name")));
                    advertisementList.add(advertisement);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e);
        }
        return advertisementList;
    }

    private List<Advertisement> getAdvertisementQuery(PreparedStatement preparedStatement) throws SQLException {
        List<Advertisement> advertisementList = new ArrayList<>();
        try(ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Advertisement advertisement = new Advertisement();
                advertisement.setId(resultSet.getLong("id"));
                advertisement.setTitle(resultSet.getString("title"));
                advertisement.setShort_description(resultSet.getString("short_description"));
                advertisement.setCreated_date(resultSet.getDate("created_date"));
                advertisement.setCategory(new Category(resultSet.getString("category_title")));
                advertisementList.add(advertisement);
            }
        }
        return advertisementList;
    }

    @Override
    public List<Advertisement> getAdvertisements(int currentPage, int recordsPerPage, int categoryId){
        List<Advertisement> advertisementList = new ArrayList<>();
        int start = currentPage * recordsPerPage - recordsPerPage;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FILTER_BY_CATEGORY_ADVERTISEMENTS)){
            preparedStatement.setInt(1, AdvertisementStatus.ACTIVE.ordinal());
            preparedStatement.setInt(2, categoryId);
            preparedStatement.setInt(3, recordsPerPage);
            preparedStatement.setInt(4, start);
            advertisementList = getAdvertisementQuery(preparedStatement);
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e);
        }
        return advertisementList;
    }

    @Override
    public int getNumberOfRows() throws SQLException {

        int numOfRows = 0;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ACTIVE_ADVERTISEMENTS_COUNT)){
            preparedStatement.setInt(1, AdvertisementStatus.ACTIVE.ordinal());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                numOfRows=resultSet.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e);
        }
        return numOfRows;
    }

    @Override
    public int getNumberOfRows(String query) throws SQLException {
        int numOfRows = 0;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ACTIVE_ADVERTISEMENTS_COUNT_FILTERED_BY_GUERY)){
            preparedStatement.setInt(1, AdvertisementStatus.ACTIVE.ordinal());
            preparedStatement.setString(2, query);
            preparedStatement.setString(3, query);
            preparedStatement.setString(4, query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                numOfRows=resultSet.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e);
        }
        return numOfRows;
    }

    @Override
    public int getNumberOfRows(int categoryId) throws SQLException {
        int numOfRows = 0;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ACTIVE_ADVERTISEMENTS_COUNT_FILTERED_BY_CATEGORY)){
            preparedStatement.setInt(1, AdvertisementStatus.ACTIVE.ordinal());
            preparedStatement.setInt(2, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                numOfRows=resultSet.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e);
        }
        return numOfRows;
    }

    @Override
    public int getNumberOfRows(String query, int categoryId) throws SQLException {
        int numOfRows = 0;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ACTIVE_ADVERTISEMENTS_COUNT_FILTERED_BY_GUERY_AND_CATEGORY)){
            preparedStatement.setInt(1, AdvertisementStatus.ACTIVE.ordinal());
            preparedStatement.setString(2, query);
            preparedStatement.setString(3, query);
            preparedStatement.setString(4, query);
            preparedStatement.setInt(5, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                numOfRows=resultSet.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e);
        }
        return numOfRows;
    }


    @Override
    public List<Advertisement> getAdvertisementsByUserAndType(User user, AdvertisementStatus advertisementStatus) throws SQLException {
        List<Advertisement> advertisementList = new ArrayList<>();

        try ( Connection connection = getConnection();
              PreparedStatement preparedStatement= connection.prepareStatement(GET_ADVERTISEMENT_BY_USER_AND_TYPE)){
            preparedStatement.setLong(2, user.getId());
            preparedStatement.setInt(1, advertisementStatus.ordinal());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Advertisement advertisement = new Advertisement();
                advertisement.setId(resultSet.getLong("id"));
                advertisement.setTitle(resultSet.getString("title"));
                advertisement.setShort_description(resultSet.getString("short_description"));
                advertisement.setDescription(resultSet.getString("description"));
                advertisement.setCreated_date(resultSet.getDate("created_date"));
                advertisement.setUser(new User(resultSet.getLong("user_id"), resultSet.getString("user_name")));
                advertisement.setCategory(new Category(resultSet.getString("category_title")));
                advertisementList.add(advertisement);
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e);
        }
        return advertisementList;
    }

    @Override
    public List<Advertisement> getAdvertisementsByType(AdvertisementStatus advertisementStatus) throws SQLException {
        List<Advertisement> advertisementList = new ArrayList<>();
        try ( Connection connection = getConnection();
              PreparedStatement preparedStatement= connection.prepareStatement(GET_ADVERTISEMENT_BY_TYPE)){


            preparedStatement.setInt(1, advertisementStatus.ordinal());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Advertisement advertisement = new Advertisement();
                advertisement.setId(resultSet.getLong("id"));
                advertisement.setTitle(resultSet.getString("title"));
                advertisement.setShort_description(resultSet.getString("short_description"));
                advertisement.setDescription(resultSet.getString("description"));
                advertisement.setCreated_date(resultSet.getDate("created_date"));
                advertisement.setUser(new User(resultSet.getLong("user_id"), resultSet.getString("user_name")));
                advertisement.setCategory(new Category(resultSet.getString("category_title")));
                advertisementList.add(advertisement);
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e);
        }
        return advertisementList;
    }

    @Override
    public Advertisement getAdvertisementsById(Long id){
        Advertisement advertisement = new Advertisement();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ADVERTISEMENT_BY_ID)){

            preparedStatement.setInt(1, AdvertisementStatus.ACTIVE.ordinal());
            preparedStatement.setInt(2, UserStatus.ACTIVE.ordinal());
            preparedStatement.setLong(3, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                advertisement.setId(resultSet.getLong("id"));
                advertisement.setTitle(resultSet.getString("title"));
                advertisement.setDescription(resultSet.getString("description"));
                advertisement.setCreated_date(resultSet.getDate("created_date"));
                advertisement.setUser(new User(resultSet.getString("user_name"),resultSet.getString("user_phone")));
                advertisement.setCategory(new Category(resultSet.getString("category_title")));
                //advertisement.setCategory(resultSet.getString("title"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e);
        }
        return advertisement;
    }

    @Override
    public boolean updateStatusById(Advertisement advertisement) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATUS_BY_ID)
        ){
            preparedStatement.setInt(1, advertisement.getStatus().ordinal());
            preparedStatement.setLong(2, advertisement.getId());
            preparedStatement.executeUpdate();
            return true;
        }  catch (SQLException e) {
            log.error("Method getUserByEmailAndPassword(): Cannot read users\n", e);
            //throw new ExceptionHandler("Cannot read users", e);
        }
        catch (ClassNotFoundException e) {
            log.error("Method getUserByEmailAndPassword(): ClassNotFoundException\n", e);
        }
        return false;
    }
}
