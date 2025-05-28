package io.github.brfernandes.emailsenderservice.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import io.github.brfernandes.emailsenderservice.models.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailListener {

    private final EmailService emailService;
    
    @KafkaListener(topics = "new-user-topic", groupId="email-group")
    public void sendWelcomerEmail(User user){
        sendWelcomerEmail(user);
    }
}
