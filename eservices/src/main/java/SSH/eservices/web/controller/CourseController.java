package SSH.eservices.web.controller;

import SSH.eservices.model.Course;
import SSH.eservices.web.dto.CourseTO;
import SSH.eservices.web.services.Itf.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *CourseController
 */
@RestController
@RequestMapping(path = "api/rest/model/courses")

@CrossOrigin("http://localhost:3000")
public class CourseController {

    @Autowired
    CourseService courseService;

    /**
     * get all courses
     *
     * @queryPath api/courses
     * @return
     */
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> getAll() throws Exception {
        return courseService.getAll();
    }

    /**
     * get a course by id
     *
     * @queryPath api/courses/{id}
     * @param id
     * @return course bean
     */
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Course get(@PathVariable("id") int id) throws Exception {
        return courseService.get(id);
    }

    /**
     * create new course
     *
     * @queryPath api/courses
     * @param courseTO
     * @return
     */
    @ResponseBody
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Course create(@RequestBody CourseTO courseTO) throws Exception {
        return courseService.create(courseTO);
    }

    /**
     *update/edit an existant course
     *
     * @queryPath api/courses
     * @param courseTO
     * @return
     */
    @ResponseBody
    @PutMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Course edit(@RequestBody CourseTO courseTO) throws Exception {
        return courseService.edit(courseTO);

    }
    /**
     * delete a course
     *
     * @queryPath api/courses/{id}
     * @param id
     * @return
     */
    @ResponseBody
    @DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean post(@PathVariable("id") int id) throws Exception {
        return courseService.delete(id);

    }





}
