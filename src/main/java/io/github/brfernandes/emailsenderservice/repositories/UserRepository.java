package io.github.brfernandes.emailsenderservice.repositories;

import io.github.brfernandes.emailsenderservice.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmailIgnoreCase(String email);

    Boolean existsByEmail(String email);
}
