package board.service;

import board.enums.AdvertisementStatus;
import board.impl.AdvertisementDaoImpl;
import board.impl.UserDaoImpl;
import board.model.Advertisement;
import board.model.Favorit;
import board.model.User;
import board.service.interfaces.AdvertisementService;
import board.util.EmailUtil;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AdvertisementServiceImpl implements AdvertisementService {
    private AdvertisementDaoImpl advertisementDao;
    private final int recordsPerPage = 5;

    public AdvertisementServiceImpl() {
        super();
        advertisementDao = new AdvertisementDaoImpl();
    }

    @Override
    public boolean save(Advertisement advertisement){
        advertisementDao.save(advertisement);
        return true;
    }

    @Override
    public boolean saveFavorite(Favorit favorit){
        return advertisementDao.saveFavorite(favorit);
    }

    @Override
    public boolean deleteFavorite(Favorit favorit){
        return advertisementDao.deleteFavorite(favorit);
    }

    @Override
    public void getAdvertisements(HttpServletRequest request) {
        String category = (String)request.getParameter("category");
        String search_text = (String)request.getParameter("search_text");

        if(search_text == null) {
            search_text = "";
        }
            if(category== null){
                category ="";
        }
        int currentPage = 1;
        try {
            if (request.getParameter("currentPage") != null) {
                currentPage = Integer.parseInt(request.getParameter("currentPage"));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        int rows=0;
        try {
            List<Advertisement> advertisementsList = new ArrayList<>();
            if (!category.equals("") || !search_text.equals("")) {
                if (!search_text.equals("") && category.equals("")) {
                        advertisementsList = advertisementDao.getAdvertisements(currentPage, recordsPerPage, search_text);
                    rows = getNumberOfRows(search_text);
                    } else if (search_text.equals("") && !category.equals("")) {
                        advertisementsList = advertisementDao.getAdvertisements(currentPage, recordsPerPage, Integer.parseInt(category));
                    rows = getNumberOfRows(Integer.parseInt(category));
                    } else  {
                        advertisementsList = advertisementDao.getAdvertisements(currentPage, recordsPerPage, search_text, Integer.parseInt(category));
                    rows = getNumberOfRows(search_text, Integer.parseInt(category));
                    }
            } else {
                advertisementsList = advertisementDao.getAdvertisements(currentPage, recordsPerPage);
                rows = getNumberOfRows();
            }


            int nOfPages = rows / recordsPerPage;
            if (nOfPages % recordsPerPage > 1) {
                nOfPages++;
            }

            request.setAttribute("noOfPages", nOfPages);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("advertisementsList", advertisementsList);

            request.setAttribute("search_text", search_text);
            request.setAttribute("category", category);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getFavoridAdvertisements(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            long userId = (Long) session.getAttribute("user_id");

            List<Advertisement> advertisementsList = new ArrayList<>();
            advertisementsList=advertisementDao.getFavoridAdvertisements(new User(userId));
            request.setAttribute("advertisementsList", advertisementsList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Advertisement> getAdvertisementsByUserAndType(User user, AdvertisementStatus advertisementStatus) throws SQLException {
        return advertisementDao.getAdvertisementsByUserAndType(user, advertisementStatus);
    }

    @Override
    public List<Advertisement> getAdvertisementsByType(AdvertisementStatus advertisementStatus) throws SQLException {
        return advertisementDao.getAdvertisementsByType(advertisementStatus);
    }

    @Override
    public Advertisement getAdvertisementsById(Long id) throws SQLException {
        return advertisementDao.getAdvertisementsById(id);
    }

    @Override
    public Advertisement getAdvertisementsById_And_UserId(Long id, Long userId) throws SQLException {
        return advertisementDao.getAdvertisementsById_And_UserId(id, userId);
    }

    @Override
    public int getNumberOfRows() throws SQLException {
        return advertisementDao.getNumberOfRows();
    }
    @Override
    public int getNumberOfRows(String query) throws SQLException {
        return advertisementDao.getNumberOfRows(query);
    }
    @Override
    public int getNumberOfRows(int categoryId) throws SQLException {
        return advertisementDao.getNumberOfRows(categoryId);
    }

    @Override
    public int getNumberOfRows(String query, int categoryId) throws SQLException {
        return advertisementDao.getNumberOfRows(query, categoryId);
    }

    @Override
    public boolean updateStatusById(Advertisement advertisement) throws SQLException {
        return advertisementDao.updateStatusById(advertisement);
    }

    @Override
    public boolean updateStatusByIdAndUserId(Advertisement advertisement) throws SQLException {
        return advertisementDao.updateStatusByIdAndUserId(advertisement);
    }


    @Override
    public boolean sendNotification(String letterBody, String email) {
        Properties properties = new Properties();
        final String fromEmail = "demo1.demo1.demo1.1@gmail.com"; //requires valid gmail id
        final String password = "csvypgdsagatvkod"; // correct password for gmail id

        try {
            properties.load(UserDaoImpl.class.getResourceAsStream("/smtp.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
        EmailUtil.sendEmail(session, email,"Notification", letterBody);
        return true;
    }


    public boolean updateById(Advertisement advertisement) throws SQLException {
        return advertisementDao.updateById(advertisement);

    }

    @Override
    public void remove(Advertisement advertisement) {

    }
}

