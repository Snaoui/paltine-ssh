package SSH.eservices.web.controller;

import SSH.eservices.model.Course;
import SSH.eservices.model.User;
import SSH.eservices.web.dto.UserTO;
import SSH.eservices.web.services.Itf.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/rest/model/")

@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    UserService userService;

    /**
     *@param email User email
     *@return User found if exists
     */
    @GetMapping(path = "/users/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserByEmail(@PathVariable("email") String email) throws Exception {
        return userService.getUserByEmail(email);
    }

    /**
     *@return List of all users
     */
    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        return userService.getUsers();
    }


    /**
     * @param email
     * @param password
     * @return User corresponding to given credentials if exists
     */
    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public User login(String email, String password) throws Exception {
        return userService.getUserByEmailAndPassword(email, password);
    }

    /**
     * return user or null if the user already exist
     *
     * @param userTo
     * @return String
     */
    @ResponseBody
    @PostMapping(path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@RequestBody UserTO userTo) throws Exception {
        try {
            return userService.createUser( userTo.toUser() );
        }catch (Exception  e){
            e.printStackTrace();
            throw new Exception( e.getMessage() );
        }
    }


    /**
     *@param userToUpdate
     * @return Updated user
     */
    @PutMapping(path = "/users/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User editUserDAO(@RequestBody UserTO userToUpdate) throws Exception {
        return userService.editUser(userToUpdate.toUser());
    }


    /**
     *@param userEmail
     * @return True if user is deleted else false
     */
    @DeleteMapping(path = "/users/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteUser(@PathVariable("email") String userEmail) {
        return userService.deleteUser(userEmail);
    }

    /**
     *
     * @param userEmail
     * @return List of all courses belong to user #userEmail
     */
    @GetMapping(path = "/courses/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> getCourses(@PathVariable("email") String userEmail) throws Exception {
        return userService.listUserCourses(userEmail);
    }

}
