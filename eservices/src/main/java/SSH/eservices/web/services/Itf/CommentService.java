package SSH.eservices.web.services.Itf;

import SSH.eservices.model.Comment;
import SSH.eservices.web.dto.CommentTO;

import java.util.List;

public interface CommentService {

	Comment get(int id) throws Exception;

	List<Comment> getAll() throws Exception;

	boolean delete(int id) throws Exception;

	Comment edit(CommentTO commentTO) throws Exception;

	Comment create(CommentTO commentTO) throws Exception;


}
