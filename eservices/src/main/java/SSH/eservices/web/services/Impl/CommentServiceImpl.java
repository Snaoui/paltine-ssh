package SSH.eservices.web.services.Impl;

import SSH.eservices.model.Comment;
import SSH.eservices.model.Course;
import SSH.eservices.model.User;
import SSH.eservices.repository.CommentRepository;
import SSH.eservices.web.dto.CommentTO;
import SSH.eservices.web.services.Itf.CommentService;
import SSH.eservices.web.services.Itf.CourseService;
import SSH.eservices.web.services.Itf.UserService;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;


public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;

    /***
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Comment get(int id) throws Exception {
        return getComment( id );
    }

    /**
     * @return
     * @throws Exception
     */
    @Override
		public List<Comment> getAll() {
			List<Comment> allCommentsList = new ArrayList<>();
			commentRepository.findAll().forEach(allCommentsList::add);
			return allCommentsList;
    }

    /***
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
		public boolean delete(int id) throws Exception {
        try {
            exists( id );
            this.commentRepository.delete( id );
            return true;
        } catch (HibernateException e) {
            throw new Exception( e.getMessage() );
        } catch (Exception e) {
            throw new Exception( "Can not delete cause: " + e.getMessage() );
        }
    }

    @Override
    public Comment edit(CommentTO commentTO) throws Exception {
        throw new Exception( "No implement Service " );
    }

    /***
     * create and save a new comment
     * verify resquest's argument values
     * @param commentTO
     * @return
     * @throws Exception
     */
    @Override
    public Comment create(CommentTO commentTO) throws Exception {
        if (commentTO.getAuthorEmail() == null) throw new IllegalArgumentException( "Author Email can not be null" );
        if (commentTO.getCourseId() < 0) throw new IllegalArgumentException( "Course id can not be null" );
        return this.createComment( commentTO );
    }

    /***
     * create and save a new comment
     * @param commentTO
     * @return
     * @throws Exception
     */
    public Comment createComment(CommentTO commentTO) throws Exception {
        Comment comment = new Comment();
        User author = this.userService.getUserByEmail( commentTO.getAuthorEmail() );
			Course course = this.courseService.get(commentTO.getCourseId());
        if (author == null) throw new Exception( "Can not create a comment without undefined author" );
        if (course == null) throw new Exception( "Can not create a comment without undefined  course" );
        comment.setAuthor( author );
        comment.setCourse( course );
        comment.setContent( commentTO.getContent() );
        this.commentRepository.save( comment );
        return comment;
    }

    /***
     * get a Comment  with  @id
     * @param id
     * @return Comment bean
     * @throws Exception if comment doesn't exists
     */
    public Comment getComment(int id) throws Exception {
        try {
            if (exists( id )) {
                return this.commentRepository.findOne( id );
						}
					return null;

        } catch (Exception e) {
            throw new Exception( e.getMessage() );
        }
    }


    /***
     * verify if comment with @id exists
     * @param id
     * @return true if exists
     * @throws Exception if comment doesn't exists
     */
    public boolean exists(int id) throws Exception {
        if (!this.commentRepository.exists( id )) {
					throw new Exception("Comment with id [" + id + "] doesn't exists");
				}
			return true;
    }
}
