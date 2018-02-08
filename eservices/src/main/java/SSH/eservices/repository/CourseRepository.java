package SSH.eservices.repository;

import SSH.eservices.model.Course;
import SSH.eservices.model.User;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository
		extends CrudRepository<Course, Integer> {
	Course findCourseByCourseCreatorIsAndName(User user,String name);
}
