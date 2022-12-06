package lxiyas.example.backend.Users.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import lxiyas.example.backend.Users.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmailAndPassword(String email, String password);

}
