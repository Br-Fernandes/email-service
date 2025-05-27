package io.github.brfernandes.emailsenderservice.services;

import io.github.brfernandes.emailsenderservice.domains.User;

public interface UserService {

    void sendWelcomeEmail(User user);

    User saveUser(User user);

    Boolean verifyToken(String token);
}
