package board.model;

import board.enums.Role;
import board.enums.UserStatus;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;


public class User implements Serializable {

    private long id;
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Name cannot be empty")
    @Min(value=1,message = "Name cannot be empty")
    private String name;
    @Min(value=1,message = "Phone cannot be empty")
    @NotEmpty(message = "Phone cannot be empty")
    private String phone;

    @NotEmpty(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[^\\s]{8,}$", message = "Password must contain numbers, uppercase, and lowercase letters")
    private String password;
    private UserStatus status;
    private Role role;
    public long getId() {
        return id;
    }

    public User() {
    }
    public User(long id, UserStatus status, Role role) {
        this.id = id;
        this.status = status;
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User(long id) {
        this.id = id;
    }

    public User(String name) {
        this.name = name;
    }
    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }



}