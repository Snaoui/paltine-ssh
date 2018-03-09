package SSH.eservices.repository;

import SSH.eservices.model.Course;
import SSH.eservices.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository
		extends CrudRepository<Course, Integer> {
	List <Course> findCourseByCourseCreator(User user);
}
