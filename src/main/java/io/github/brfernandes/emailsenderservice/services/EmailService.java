package io.github.brfernandes.emailsenderservice.services;

public interface EmailService {

    void sendSimpleEmailMessage(String name, String to, String token);

    void sendMineMessageWithAttachment(String name, String to, String token);

    void sendMineMessageWithEmbeddedFiles(String name, String to, String token);

    void sendHtmlEmail(String name, String to, String token);

    void sendHtmlEmailWithEmbeddedFiles(String name, String to, String token);
}
