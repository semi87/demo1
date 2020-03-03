package board.dao.interfaces;
import board.enums.UserStatus;
import board.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    //create
    boolean save(User user) throws SQLException;
    User getUserByEmailAndPassword(String email, String password) throws SQLException;
    long getUserIdByEmail(String email) throws SQLException;
    User getUserById(Long id) throws SQLException;
    boolean activateUser(String token);
    boolean deleteTokenByUserId(Long id);
    boolean updateUserStatus(Long id, UserStatus status);
    boolean addRegisterToken(long user_id, String token);
    boolean sendActivateLetter(String context, String user_email, String token) throws IOException;
    List<User> getAllUsers();
    boolean updateUserByID(User user);
}