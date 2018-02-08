package SSH.eservices.repository;

import SSH.eservices.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository
		extends CrudRepository<Comment, Integer> {
}
