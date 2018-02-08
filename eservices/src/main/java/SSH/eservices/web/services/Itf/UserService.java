package SSH.eservices.web.services.Itf;

import SSH.eservices.model.Course;
import SSH.eservices.model.Role;
import SSH.eservices.model.User;

import java.util.List;

public interface UserService {

	User createUser(User newUser) throws Exception;

	User createUser(String email, String userName, String password, Role role) throws Exception;

	User editUser(User userToEdit) throws Exception;

	boolean deleteUser(String userEmail);

	List<User> getUsers();

	User getUserByEmail(String email) throws Exception;

	//List<User> getUserByRole(Role role);

	User getUserByEmailAndPassword(String email, String password);

	boolean exists(String email);

	List<Course> listAllCourses();

	List<Course> listUserCourses(String email) throws Exception;
}
