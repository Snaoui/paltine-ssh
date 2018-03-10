package SSH.eservices.web;

import SSH.eservices.model.*;
import SSH.eservices.repository.*;
import SSH.eservices.web.dto.PathTO;
import SSH.eservices.web.dto.SurveyTO;
import SSH.eservices.web.services.Itf.IPathService;
import SSH.eservices.web.services.Itf.SurveyService;
import SSH.eservices.web.services.Itf.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.geo.Point;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * App config!
 */
@SpringBootApplication
@EntityScan(basePackages = "SSH.eservices.model")
@EnableJpaRepositories(basePackages = {"SSH.eservices.repository"})
@ComponentScan(basePackages = {"SSH.eservices.web"})
public class App
        implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private IPathService pathService;

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private PathRepository pathRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AnswerRepository answerRepository;

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    @Override
    public void run(String... strings) throws Exception {
        // TODO auto-generated

        logger.info("Creating user1...");
        User user1 = userService.createUser("admin@admin.fr", "admin", "password", Role.ADMIN_ROLE);
        logger.info("Creating user2...");
        User user2 = userService.createUser("che@che.fr", "cherif", "password", Role.SIMPLE_USER_ROLE);
        logger.info("Creating course...");
        //courseService.create()


        Comment comment = commentRepository.save(new Comment());
        Comment comment2 = commentRepository.save(new Comment());
        Comment comment3 = commentRepository.save(new Comment());

        logger.info("comment id " + comment.getId());

        Path p2 = new Path();

        p2.setFrom(new Point(50.609485775888054, 3.157536350190639));
        p2.setTo(new Point(50.609485775888058, 3.157536350190629));
        p2 = pathRepository.save(p2);
        logger.info("path id " + p2.getId());

        Survey survey3 = new Survey();
        survey3.setLabel("Question 3");
        Answer answer1 = new Answer();
        answer1.setLabel("Response 1");
        answer1.setCorrect(true);
        answerRepository.save(answer1);
        Answer answer2  = new Answer();
        answer2.setLabel("Response 2");
        answerRepository.save(answer2);
        Answer answer3  = new Answer();
        answer3.setLabel("Response 3");
        answerRepository.save(answer3);
        Answer answer4  = new Answer();
        answer4.setLabel("Response 4");
        answerRepository.save(answer4);
        List<Answer> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);
        survey3.setAnswers(answers);
        survey3 = surveyRepository.save(survey3);

        Survey survey4 = new Survey();
        survey4.setLabel("Question 4");
        Answer answer5 = new Answer();
        answer5.setLabel("Response 1");
        answer5.setCorrect(true);
        answerRepository.save(answer5);
        Answer answer6  = new Answer();
        answer6.setLabel("Response 2");
        answerRepository.save(answer6);
        Answer answer7  = new Answer();
        answer7.setLabel("Response 3");
        answerRepository.save(answer7);
        Answer answer8  = new Answer();
        answer8.setLabel("Response 4");
        answerRepository.save(answer8);
        answers.clear();
        answers.add(answer5);
        answers.add(answer6);
        answers.add(answer7);
        answers.add(answer8);
        survey4.setAnswers(answers);
        survey4 = surveyRepository.save(survey4);

        SurveyTO surveyTO3 = new SurveyTO();
        surveyTO3.setSurvey(survey3);
        surveyTO3.setPathId(p2.getId());
        survey3 = surveyService.create(surveyTO3);

        SurveyTO surveyTO4 = new SurveyTO();
        surveyTO4.setSurvey(survey4);
        surveyTO4.setPathId(p2.getId());
        survey4 = surveyService.create(surveyTO4);

        p2.setSurveys(Arrays.asList(survey3, survey4));
        p2 = pathRepository.save(p2);

        PathTO pathTO2 = new PathTO();
        pathTO2.setPath(p2);
        pathTO2.setUserEmail(user2.getEmail());
        p2 = pathService.create(pathTO2);

        Survey survey = new Survey();
        survey.setLabel("Question 1");
        Answer answer9 = new Answer();
        answer9.setLabel("Response 1");
        answer9.setCorrect(true);
        answerRepository.save(answer9);
        Answer answer10  = new Answer();
        answer10.setLabel("Response 2");
        answerRepository.save(answer10);
        Answer answer11 = new Answer();
        answer11.setLabel("Response 3");
        answerRepository.save(answer11);
        Answer answer12 = new Answer();
        answer12.setLabel("Response 4");
        answerRepository.save(answer12);
        answers.clear();
        answers.add(answer9);
        answers.add(answer10);
        answers.add(answer11);
        answers.add(answer12);
        survey.setAnswers(answers);
        survey = surveyRepository.save(survey);
        logger.info("path id " + survey.getLabel());

        Survey survey2 = new Survey();
        survey2.setLabel("Question 2");
        Answer answer13 = new Answer();
        answer13.setLabel("Response 1");
        answer13.setCorrect(true);
        answerRepository.save(answer13);
        Answer answer14  = new Answer();
        answer14.setLabel("Response 2");
        answerRepository.save(answer14);
        Answer answer15  = new Answer();
        answer15.setLabel("Response 3");
        answerRepository.save(answer15);
        Answer answer16  = new Answer();
        answer16.setLabel("Response 4");
        answerRepository.save(answer16);
        answers.clear();
        answers.add(answer13);
        answers.add(answer14);
        answers.add(answer15);
        answers.add(answer16);
        survey2.setAnswers(answers);
        survey2 = surveyRepository.save(survey2);
        logger.info("path id " + survey2.getLabel());

        Path p = new Path();

        p.setFrom(new Point(50.609485775888034, 3.157536350190239));
        p.setTo(new Point(50.609485765888054, 3.157536350191639));

        p = pathRepository.save(p);
        logger.info("path id " + p.getId());

        SurveyTO surveyTO1 = new SurveyTO();
        surveyTO1.setSurvey(survey);
        surveyTO1.setPathId(p.getId());
        survey = surveyService.create(surveyTO1);

        SurveyTO surveyTO2 = new SurveyTO();
        surveyTO2.setSurvey(survey2);
        surveyTO2.setPathId(p.getId());
        survey2 = surveyService.create(surveyTO2);

        p.setSurveys(Arrays.asList(survey, survey2));

        PathTO pathTO = new PathTO();
        pathTO.setPath(p);
        pathTO.setUserEmail(user1.getEmail());
        p = pathService.create(pathTO);
        logger.info("path p id " + p.getId());

        Course course = new Course();
        course.setCourseCreator(user1);
        course.setComments(Arrays.asList(comment, comment2, comment3));
        course.setDuration(2.5);
        course.setName("myFirstCourse");
        course.setNote(5.0);
        course.setPaths(Arrays.asList(p, p2));
        logger.info("course id " + course.getId());
        course = courseRepository.save(course);

        userRepository.save(user1);
    }
}
