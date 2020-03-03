package board.service;

import board.enums.Role;
import board.enums.UserStatus;
import board.impl.UserDaoImpl;
import board.model.User;
import board.service.interfaces.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    private UserDaoImpl userDaoImpl;
    private static Logger log = Logger.getLogger(UserServiceImpl.class.getName());

    public UserServiceImpl() {
        super();
        userDaoImpl = new UserDaoImpl();
    }

    @Override
    public boolean authorizationUser(HttpServletRequest request) {
        String message="";
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = userDaoImpl.getUserByEmailAndPassword(email, get_SHA_1_SecurePassword(password));
        if (user != null) {
            if (user.getStatus() == UserStatus.ACTIVE) {
                long user_id = user.getId();
                String name = user.getName();
                Role user_role = user.getRole();
                HttpSession session = request.getSession();
                session.setAttribute("user_id", user_id);
                session.setAttribute("user_name", name);
                session.setAttribute("user_role", user_role);
                return true;
            } else if (user.getStatus() == UserStatus.NEW) {
                message = "Account is not activated. Please check email";
            }
        } else {
            message = "Wrong email or password";
        }
        request.setAttribute("message", message);
        return false;
    }

    @Override
    public long getUserIdByEmail(String email)  {
        return userDaoImpl.getUserIdByEmail(email);
    }

    @Override
    public User getUserById(Long user_id) {
        return userDaoImpl.getUserById(user_id);
    }

    @Override
    public void activateAccount(HttpServletRequest request){
        String token = request.getParameter("token");
        String message;
        long userId = 0;
        if (token != null) {
            if (userDaoImpl.activateUser(token)) {
                message = "Thank you. Account is active";
            } else {
                message = "Error. Please try later";
            }
        } else {
            message = "Oops ....";
        }
        request.setAttribute("message", message);

    }

    @Override
    public boolean updateUserByID(User user) {
        return userDaoImpl.updateUserByID(user);
    }



    @Override
    public List<User> getAllUsers() {
        return userDaoImpl.getAllUsers();
    }

    @Override
    public boolean save(HttpServletRequest request) throws IOException {
        Set<ConstraintViolation<User>> violations = null;
        ArrayList<String> errors = new ArrayList<>();
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        /*if(!password.equals(confirmPassword)){
            errorMsg = "Conform password is different";
        }*/
        request.setAttribute("email", email);
        request.setAttribute("name", name);
        request.setAttribute("phone", phone);
        request.setAttribute("password", password);
        request.setAttribute("confirm_password", confirmPassword);

        long userId = getUserIdByEmail(email);
        if (userId == 0) {
            User user = new User();
            user.setEmail(email.trim());
            user.setName(name.trim());
            user.setPhone(phone.trim());
            user.setPassword(password.trim());
            user.setRole(Role.USER);
            user.setStatus(UserStatus.NEW);

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            violations = validator.validate(user);
            if (violations.isEmpty()) {
                user.setPassword(get_SHA_1_SecurePassword(user.getPassword()
                ));
                if (userDaoImpl.save(user)) {
                    userId = userDaoImpl.getUserIdByEmail(user.getEmail());
                    if (userId != 0) {
                        String token = UUID.randomUUID().toString().replaceAll("-", "");
                        userDaoImpl.addRegisterToken(userId, token);
                        URL url = new URL(request.getRequestURL().toString());
                        String domain = url.getProtocol().concat("://").concat(url.getHost()).concat(request.getContextPath().toString());
                        userDaoImpl.sendActivateLetter(domain, user.getEmail(), token);
                        request.setAttribute("message", "Success. Please check email");

                    }
                }
            } else {
                for (ConstraintViolation<User> violation : violations) {
                    errors.add(violation.getMessageTemplate());
                }
            }
        } else {
            errors.add("Email is already in use");
        }
        request.setAttribute("errors", errors);
        return true;
    }

    private static String get_SHA_1_SecurePassword(String passwordToHash) {
        final byte salt[] = {72, 24, -112, 119, 77, 32, -75, 101, -16, 0, -102, 28, 48, -4, 58, -51};
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    /*//Add salt
    private static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }*/

}

