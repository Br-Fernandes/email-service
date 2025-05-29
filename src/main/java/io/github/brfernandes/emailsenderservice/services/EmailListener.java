package io.github.brfernandes.emailsenderservice.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import io.github.brfernandes.emailsenderservice.models.Confirmation;
import io.github.brfernandes.emailsenderservice.models.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailListener {

    private final EmailService emailService;
    
    @KafkaListener(topics = "new-user-topic", groupId="email-group")
    public void sendWelcomerEmail(@Payload User user){
        Confirmation confirmation = new Confirmation();
        emailService.sendHtmlEmail(user.getName(), user.getEmail(), confirmation.getToken());
    }
}
