package SSH.eservices.web.dto;

import SSH.eservices.model.Role;
import SSH.eservices.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserTO {

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    public String username;
    public String password;
    public String email;

    public Role userRole = Role.SIMPLE_USER_ROLE;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public User toUser(){
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setEmail(this.email);
        user.setPassword(password);
        user.setUsername(this.username);
        user.setUserRole(this.userRole);
        return user;
    }
}
