package SSH.eservices.repository;

import SSH.eservices.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

    User findUserByEmailAndPassword(String email, String password);
    User findByEmail(String email);
    User findOne(String email);

}
