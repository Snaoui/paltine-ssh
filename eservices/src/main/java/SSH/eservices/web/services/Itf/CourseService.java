package SSH.eservices.web.services.Itf;

import SSH.eservices.model.Course;
import SSH.eservices.web.dto.CourseTO;

import java.util.List;

public interface CourseService {
    /**
     * get a course associate to id
     *
     * @param id
     * @return
     * @throws Exception if course not found
     */
		Course get(int id) throws Exception;

    /**
     * get list of all courses
     *
     * @return
     * @throws Exception
     */
		List<Course> getAll() throws Exception;

    /**
     * create and save a new course
     *
     * @param courseTO
     * @return
     * @throws Exception
     */
		Course create(CourseTO courseTO) throws Exception;

    /**
     * edit a course
     *
     * @param  courseTO
     * @return
     * @throws Exception
     */
		Course edit(CourseTO courseTO) throws Exception;

    /**
     * delete a course
     *
     * @param id
     * @return
     * @throws Exception if course not found
     */
		boolean delete(int id) throws Exception;
}

