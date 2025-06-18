package io.github.brfernandes.emailsenderservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.github.brfernandes.emailsenderservice.models.Confirmation;

@Repository
public interface ConfirmationRepository extends MongoRepository<Confirmation, String> {

    Confirmation findByToken(String token);

    Confirmation findByUserEmail(String email);
}
