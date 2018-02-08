package SSH.eservices.web.dto;

import SSH.eservices.model.Role;
import SSH.eservices.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserTO {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public String username;
    public String password;
    public String email;

    public Role userRole;

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
        User user = new User();
        user.setEmail(this.email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setUsername(this.username);
        user.setUserRole(this.userRole);
        return user;
    }
}
