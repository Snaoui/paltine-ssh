package SSH.eservices.repository;

import SSH.eservices.model.Path;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PathRepository extends CrudRepository<Path, Integer> {
    boolean existsPathByIdEquals(int id);
}
