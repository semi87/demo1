package board.service.interfaces;

import board.dao.ExceptionHandler;
import board.enums.AdvertisementStatus;
import board.model.Advertisement;
import board.model.Favorit;
import board.model.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public interface AdvertisementService {

        //create
        boolean save(Advertisement advertisement) throws ExceptionHandler;
        boolean saveFavorite(Favorit favorit) throws ExceptionHandler;
        boolean deleteFavorite(Favorit favorit) throws ExceptionHandler;

        //read
        void getAdvertisements(HttpServletRequest request);
        void getFavoridAdvertisements(HttpServletRequest request);


        List<Advertisement> getAdvertisementsByUserAndType(User user, AdvertisementStatus advertisementStatus) throws SQLException;
        List<Advertisement> getAdvertisementsByType(AdvertisementStatus advertisementStatus) throws SQLException;
        Advertisement getAdvertisementsById(Long id) throws SQLException;
        Advertisement getAdvertisementsById_And_UserId(Long id, Long userId) throws SQLException;
        int getNumberOfRows() throws SQLException;
        int getNumberOfRows(String query) throws SQLException;
        int getNumberOfRows(int categoryId) throws SQLException;
        int getNumberOfRows(String query,int categoryId) throws SQLException;
        boolean sendNotification(String letterBody, String email);


        boolean updateStatusById(Advertisement advertisement) throws SQLException, ExceptionHandler;
        boolean updateStatusByIdAndUserId(Advertisement advertisement) throws SQLException, ExceptionHandler;

        //update
        boolean updateById(Advertisement advertisement) throws SQLException;

        //delete
        void remove(Advertisement advertisement) throws SQLException;

}
