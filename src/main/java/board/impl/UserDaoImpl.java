package board.impl;

import board.dao.DBConnectionManager;
import board.dao.interfaces.UserDAO;
import board.enums.Role;
import board.enums.UserStatus;
import board.model.User;
import board.util.EmailUtil;
import org.apache.log4j.Logger;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserDaoImpl extends DBConnectionManager implements UserDAO {
    private static final String SAVE_USER = "INSERT INTO users (email,name,phone, password, status, role) VALUES (?,?,?,?,?,?)";
    private static final String SAVE_TOKEN = "INSERT INTO tokens (user_id, token) VALUES (?,?)";
    private static final String GET_USER_BY_EMAIL_PASSWORD = "SELECT id, name,role, email,status FROM users WHERE email=? and password = ? LIMIT 1";
    private static final String GET_ALL_USERS = "SELECT id, name,role, email,status,phone FROM users";
    private static final String GET_USER_BY_ID = "SELECT id, name, email,phone FROM users WHERE  id = ? and status=? LIMIT 1";
    private static final String FIND_USER_ID_BY_EMAIL = "SELECT id FROM users WHERE email=? LIMIT 1";
    private static final String GET_USER_ID_BY_TOKEN = "SELECT user_id FROM tokens WHERE token=? LIMIT 1";
    private static final String DELETE_TOKEN_BY_USER = "DELETE FROM tokens  WHERE user_id=?";
    private static final String UPDATE_USER_STATUS_BY_USER_ID = "UPDATE users SET status =? WHERE id=?";
    private final String UPDATE_USER_BY_ID ="UPDATE users SET role=?, status=? WHERE id=?";
    public static final Logger log = Logger.getLogger(UserDaoImpl.class);


    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL_PASSWORD)){
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(Role.values()[resultSet.getInt("role")]);
                user.setStatus(UserStatus.values()[resultSet.getInt("status")]);
            }
        } catch (SQLException e) {
            log.error("Method getUserByEmailAndPassword(): Cannot read users\n", e);
        }
        catch (ClassNotFoundException e) {
            log.error("Method getUserByEmailAndPassword(): ClassNotFoundException\n", e);
        }
        return user;
    }

    @Override
    public long getUserIdByEmail(String email) {
        long user_id = 0;
        try( Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_ID_BY_EMAIL);) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user_id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            log.error("Method getUserByEmailAndPassword(): Cannot read users\n", e);
        }
        catch (ClassNotFoundException e) {
            log.error("Method getUserByEmailAndPassword(): ClassNotFoundException\n", e);
        }
        return user_id;
    }

    @Override
    public User getUserById(Long user_id) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)){
            preparedStatement.setLong(1, user_id);
            preparedStatement.setInt(2, UserStatus.ACTIVE.ordinal());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));

            }
        } catch (SQLException e) {
            log.error("Method getUserById(): Cannot read users\n", e);
        }
        catch (ClassNotFoundException e) {
            log.error("Method getUserById(): ClassNotFoundException\n", e);
        }
        return user;
    }

    @Override
    public boolean activateUser(String token){
        long userId = 0;
        try(Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_ID_BY_TOKEN)){
            preparedStatement.setString(1, token);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userId = resultSet.getLong("user_id");
            }
            if(userId!=0){
                deleteTokenByUserId(userId);
                updateUserStatus(userId,UserStatus.ACTIVE);
                return true;
            }

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Method getUserIdByToken(): Cannot read tokens\n", e);
        }
        return false;
    }

    @Override
    public boolean deleteTokenByUserId(Long id) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TOKEN_BY_USER)){
            preparedStatement.setLong(1, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            log.error("Method getUserIdByToken(): Cannot read tokens\n", e);
        }
        catch (ClassNotFoundException e) {
            log.error("Method getUserIdByToken(): Cannot read tokens\n", e);
        }
        return false;
    }

    @Override
    public boolean updateUserStatus(Long id, UserStatus status) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_STATUS_BY_USER_ID)){
            preparedStatement.setLong(1, status.ordinal());
            preparedStatement.setLong(2, id);
            return preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            log.error("Method getUserIdByToken(): Cannot read tokens\n", e);
        }
        return false;
    }


    @Override
    public boolean addRegisterToken(long user_id, String token) {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement  = connection.prepareStatement(SAVE_TOKEN)){
            preparedStatement.setLong(1, user_id);
            preparedStatement.setString(2, token);
            preparedStatement.executeUpdate();
            return true;
        }  catch (SQLException e) {
            log.error("Method addRegisterToken(): Cannot read users\n", e); }
        catch (ClassNotFoundException e) {
            log.error("Method addRegisterToken(): ClassNotFoundException\n", e);
        }
        return false;
    }

    @Override
    public boolean sendActivateLetter(String contextPath, String email, String token) {
        Properties properties = new Properties();
        final String fromEmail = "demo1.demo1.demo1.1@gmail.com"; //requires valid gmail id
            final String password = "csvypgdsagatvkod"; // correct password for gmail id

        try {
            properties.load(UserDaoImpl.class.getResourceAsStream("/smtp.properties"));
        } catch (IOException e) {
           log.error(e);
        }
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        String letterBody="You registered on site demo1. For activate your account please click on link : <a href='"+contextPath+"/activate?token="+token+"'>Activate</a>";
        Session session = Session.getInstance(properties, auth);
        EmailUtil.sendEmail(session, email,"Activate link", letterBody);
        return true;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList= new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setRole(Role.values()[resultSet.getInt("role")]);
                user.setStatus(UserStatus.values()[resultSet.getInt("status")]);
                userList.add(user);
            }
        } catch (SQLException e) {
            log.error("Method getUserById(): Cannot read users\n", e);
            //throw new ExceptionHandler("Cannot read users", e);
        }
        catch (ClassNotFoundException e) {
            log.error("Method getUserById(): ClassNotFoundException\n", e);
        }
        return userList;
    }

        @Override
        public boolean updateUserByID(User user) {
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BY_ID)
            ){
                preparedStatement.setInt(1, user.getRole().ordinal());
                preparedStatement.setInt(2, user.getStatus().ordinal());
                preparedStatement.setLong(3, user.getId());
                preparedStatement.executeUpdate();
                return true;
            }  catch (SQLException e) {
                log.error("Method getUserByEmailAndPassword(): Cannot read users\n", e);
            }
            catch (ClassNotFoundException e) {
                log.error("Method getUserByEmailAndPassword(): ClassNotFoundException\n", e);
            }
            return false;
        }


    @Override
    public boolean save(User user) {
        try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER)
        ) {

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, UserStatus.NEW.ordinal());
            preparedStatement.setInt(6, Role.USER.ordinal());
            preparedStatement.execute();
            return true;
        }  catch (SQLException e) {
            log.error("Method save(): Cannot insert new user\n", e);
            //throw new ExceptionHandler("Cannot insert new user", e);
        }
        catch (ClassNotFoundException e) {
            log.error("Method save(): ClassNotFoundException\n", e);
        }
        return false;
    }
}
