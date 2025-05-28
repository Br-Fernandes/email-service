package io.github.brfernandes.emailsenderservice.repositories;

import io.github.brfernandes.emailsenderservice.models.Confirmation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationRepository extends MongoRepository<Confirmation, String> {

    Confirmation findByToken(String token);
}
