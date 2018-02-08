package SSH.eservices.web.services.Impl;

import SSH.eservices.model.Course;
import SSH.eservices.model.User;
import SSH.eservices.repository.CourseRepository;
import SSH.eservices.web.dto.CourseTO;
import SSH.eservices.web.services.Itf.CourseService;
import SSH.eservices.web.services.Itf.UserService;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("courseService")
public class CourseServiceImpl implements CourseService {

     @Autowired
     CourseRepository courseRepository;

     @Autowired
     UserService userService;

    /**
     * get a course associate to id
     *
     * @param id
     * @return
     * @throws Exception if course not found
     */
    @Override
		public Course get(int id) throws Exception {
			return getCourse(id);
    }

    /**
     * get list of all courses
     *
     * @return
     * @throws Exception
     */
    @Override
		public List<Course> getAll() {
			List<Course> allCoursesList = new ArrayList<>();
			courseRepository.findAll().forEach(allCoursesList::add);
			return allCoursesList;
    }

    /**
     * create and save a new course
     *
     * @param courseTO
     * @return
     * @throws Exception
     */
    @Override
		public Course create(CourseTO courseTO) throws Exception {
        if(courseTO==null || courseTO.getUserEmail() == null || courseTO.getCourse()==null ){
            throw new IllegalArgumentException("Can not create a course with invalid arguments");
        }
			return createCourse(courseTO);
    }

    /**
     * edit a course
     *
     * @param courseTO
     * @return
     * @throws Exception
     */
    @Override
		public Course edit(CourseTO courseTO) {
        return null;
    }

    /**
     * delete a course
     *
     * @param id
     * @return
     * @throws Exception if course not found
     */
    @Override
		public boolean delete(int id) throws Exception {
        if(!exists( id )){
            throw new Exception( "Course with id ["+id+"] doesn't exists !\"" );
        }
			return delete(getCourse(id));
    }

    /**
     * verify if  course associate with @param id exist
     *
     * @param id
     * @return boolean
     */
    private boolean exists(int id) {
        return courseRepository.exists(id);
    }

    private Course getCourse(int id) throws Exception {
        if(!exists( id )){
            throw new Exception( "Can not find course with id "+Integer.toString( id ) );
        }
        return courseRepository.findOne(id);
    }

    private Course createCourse(CourseTO courseTO) throws Exception {
        User user = userService.getUserByEmail( courseTO.getUserEmail());
        if(user==null){
            throw new IllegalArgumentException(
                "Can not create course associate to user [" + courseTO.getUserEmail() + "]. Cause: user doesn't exist");
        }
        Course course = courseTO.getCourse();
        course.setCourseCreator(user);
        course = courseRepository.save(course);
        //course = courseRepository.findCourseByCourseCreatorIsAndName(user,course.getName());
        return course;

    }

    private boolean delete(Course course){
        try{
            courseRepository.delete(course);
            return true;
        }catch (HibernateException he) {
            throw new IllegalArgumentException( "Unknown course to delete !" );
        }
    }
}
