package io.github.brfernandes.emailsenderservice.services;

import io.github.brfernandes.emailsenderservice.models.Order;
import io.github.brfernandes.emailsenderservice.models.User;

public interface UserService {

    void sendWelcomeEmail(User user);

    void sendConfirmedOrder(Order order);

    Boolean verifyToken(String token);
}
