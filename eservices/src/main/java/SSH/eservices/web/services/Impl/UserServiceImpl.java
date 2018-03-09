package SSH.eservices.web.services.Impl;

import SSH.eservices.model.Course;
import SSH.eservices.model.Role;
import SSH.eservices.model.User;
import SSH.eservices.repository.CourseRepository;
import SSH.eservices.repository.UserRepository;
import SSH.eservices.web.services.Itf.UserService;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

@Component("userService")
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	PasswordEncoder bCryptPasswordEncoder;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private PasswordEncoder encoder;



	@Override
	public User createUser(User newUser) {
		User createdUser = new User();
		if(newUser == null || newUser.getEmail() == null || newUser.getEmail().isEmpty()){
			throw new IllegalArgumentException("Cannot create user without his username");
		}
		if(exists(newUser.getEmail())){
			throw new IllegalArgumentException("User with email ["+ newUser.getEmail()+"] already exists");
		}
		try{
			newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
			createdUser = userRepository.save(newUser);
		}catch (Exception e){
			e.printStackTrace();
		}
		return createdUser;
	}

	protected boolean exist(Role role) {
		for (Role curRole : Role.values()) {
			if (curRole.equals(role)) {
				return true;
			}
		}
		return false;
	}


	@Override
	public User createUser(String email, String userName, String password, Role role) {
		User user = new User();
		user.setEmail(email);
		user.setUsername(userName);
		user.setPassword(password);

		if (role == null || !exist(role)) {
			user.setUserRole(Role.SIMPLE_USER_ROLE);
		} else {
			user.setUserRole(role);
		}
		return this.createUser(user);
	}

	@Override
	public User editUser(User userToEdit) throws Exception {
		User foundedUser = getUserByEmail(userToEdit.getEmail());
		foundedUser.setUsername(userToEdit.getUsername());
		foundedUser.setEmail(userToEdit.getEmail());
		foundedUser.setPassword(bCryptPasswordEncoder.encode(userToEdit.getPassword()));
		foundedUser.setUserRole(userToEdit.getUserRole());
		return entityManager.merge(foundedUser);
	}

	@Override
	public boolean deleteUser(String userEmail) {
		try{
			userRepository.delete(userEmail);
			return true;
		}catch (EmptyResultDataAccessException e){
			LOGGER.debug("Nothing has been deleted", e);
			return false;
		} catch (HibernateException he){
			throw new IllegalArgumentException("Unknown user to delete");
		}
	}

	@Override
	public List<User> getUsers() {
		List<User> allUsersList = new ArrayList<>();
		userRepository.findAll().forEach(allUsersList::add);
		return allUsersList;
	}

	@Override
	public User getUserByEmail(String email) throws Exception {
		if(!exists(email)){
			throw new Exception("User with email ["+email+"] doesn't exists");
		}
		return userRepository.findByEmail(email);
	}


	/*@Override
	public List<User> getUserByRole(Role role) {
		return userRepository.findUsersByRole(role);
	}*/

	@Override
	public User getUserByEmailAndPassword(String email, String password) throws Exception {
		User foundedUser = userRepository.findByEmail(email);
		String pass = encoder.encode(password);
		if (foundedUser == null) {
			throw new Exception("User not found !");
		}else if(!encoder.matches(password, foundedUser.getPassword())){
			throw new Exception("User not found: bad credentials !");
		}
		return foundedUser;
	}

	@Override
	public boolean exists(String email) {
		return userRepository.exists(email);
	}

	@Override
	public List<Course> listAllCourses() {
		List<Course> allCoursesList = new ArrayList<>();
		courseRepository.findAll().forEach(allCoursesList::add);
		return allCoursesList;
	}

	@Override
	public List<Course> listUserCourses(String email) throws Exception {
		User user = userRepository.findOne(email);
		List<Course> courses = courseRepository.findCourseByCourseCreator(user);
		if(user==null) {
			throw new Exception("Can not get course list: User not found");
		}
		else{
			return courses;
		}
	}
}
