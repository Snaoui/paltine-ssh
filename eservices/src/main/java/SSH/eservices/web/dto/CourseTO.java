package SSH.eservices.web.dto;

import SSH.eservices.model.Course;

import java.util.List;

public class CourseTO {
    public  String userEmail;
    public Course course;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourses(Course courses) {
        this.course = courses;
    }
}
