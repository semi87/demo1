package board.service.interfaces;

import board.model.User;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserService {

        //create
        List<User> getAllUsers();
        boolean save(HttpServletRequest request) throws IOException;
        boolean authorizationUser(HttpServletRequest request);
        long getUserIdByEmail(String email) throws SQLException;
        User getUserById(Long user_id) throws SQLException;
        void activateAccount(HttpServletRequest request);
        boolean updateUserByID(User user);
}
