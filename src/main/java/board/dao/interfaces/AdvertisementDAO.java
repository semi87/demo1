package board.dao.interfaces;

import board.dao.ExceptionHandler;
import board.enums.AdvertisementStatus;
import board.model.Advertisement;
import board.model.Favorit;
import board.model.User;

import java.sql.SQLException;
import java.util.List;


public interface AdvertisementDAO {

    //create
    void save(Advertisement advertisement) throws ExceptionHandler;
    boolean saveFavorite(Favorit favorit) throws ExceptionHandler;
    boolean deleteFavorite(Favorit favorit) throws ExceptionHandler;
    //read
    List<Advertisement> getFavoridAdvertisements(User user);
    List<Advertisement> getAdvertisements(int currentPage, int recordsPerPage);
    List<Advertisement> getAdvertisements(int currentPage, int recordsPerPage, String query,  int category_id);
    List<Advertisement> getAdvertisements(int currentPage, int recordsPerPage, String query);
    List<Advertisement> getAdvertisements(int currentPage, int recordsPerPage, int category_id);

    List<Advertisement> getAdvertisementsByUserAndType(User user, AdvertisementStatus advertisementStatus) throws SQLException;
    List<Advertisement> getAdvertisementsByType(AdvertisementStatus advertisementStatus) throws SQLException;
    Advertisement getAdvertisementsById(Long id) throws SQLException;
    Advertisement getAdvertisementsById_And_UserId(Long id, Long userId) throws SQLException;
    int getNumberOfRows() throws SQLException;
    int getNumberOfRows(String query) throws SQLException;
    int getNumberOfRows(int categoryId) throws SQLException;
    int getNumberOfRows(String query,int categoryId) throws SQLException;
    boolean updateStatusById(Advertisement advertisement) throws SQLException;
    boolean updateStatusByIdAndUserId(Advertisement advertisement) throws SQLException;
    boolean updateById(Advertisement advertisement) throws SQLException;

}