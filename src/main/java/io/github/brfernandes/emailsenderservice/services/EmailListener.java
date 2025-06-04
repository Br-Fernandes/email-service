
package io.github.brfernandes.emailsenderservice.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.brfernandes.emailsenderservice.models.Confirmation;
import io.github.brfernandes.emailsenderservice.models.Order;
import io.github.brfernandes.emailsenderservice.models.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailListener {
    private final EmailSenderService emailService;
    private final ObjectMapper objectMapper;
    
    @KafkaListener(topics = "new-user-topic", groupId="email-consumer")
    public void sendWelcomerEmail(User user){
        Confirmation confirmation = new Confirmation();
        emailService.sendWelcomeEmail(user.getName(), user.getEmail(), confirmation.getToken());
    }

    @KafkaListener(topics = "new-order-topic", groupId = "email-consumer")
    public void send(Order order) {
        emailService.sendConfirmedOrderEmail(order.getUsername(), order.getProductName(), order.getTo(), order.getStatus());
    }
}
