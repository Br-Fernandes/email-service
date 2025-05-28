package io.github.brfernandes.emailsenderservice.services;

public interface EmailSenderService {

    void sendSimpleEmailMessage(String name, String to, String token);

    void sendWelcomeEmail(String name, String to, String token);

    void sendConfirmedOrderEmail(String username, String productName, String status, String to);
}
