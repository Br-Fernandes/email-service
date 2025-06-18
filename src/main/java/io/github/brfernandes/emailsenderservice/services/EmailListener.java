
package io.github.brfernandes.emailsenderservice.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.brfernandes.emailsenderservice.models.Confirmation;
import io.github.brfernandes.emailsenderservice.models.Order;
import io.github.brfernandes.emailsenderservice.models.User;
import io.github.brfernandes.emailsenderservice.repositories.ConfirmationRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailListener {
    private final EmailSenderService emailService;
    private final ConfirmationRepository confirmationRepository;
    private final ObjectMapper mapper;
    
    @KafkaListener(topics = "new-user-topic", groupId="email-user-consumer")
    public void sendWelcomerEmail(String message){
        try {
            User user = mapper.readValue(message, User.class);
            Confirmation confirmation = confirmationRepository.findByUserEmail(user.getEmail());

            emailService.sendWelcomeEmail(user.getName(), user.getEmail(), confirmation.getToken());
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "new-order-topic", groupId = "email-order-consumer")
    public void send(String message) {
        Order order;
        try {
            order = mapper.readValue(message, Order.class);
            emailService.sendConfirmedOrderEmail(order.getUsername(), order.getProductName(), order.getTo(), order.getStatus());

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    // @KafkaListener(topics = "new-order-topic", groupId = "email-order-consumer")
    // public void send(Order order) {
    //     if (order == null) {
    //         System.err.println("Received null order");
    //         return;
    //     }
    //     emailService.sendConfirmedOrderEmail(order.getUsername(), order.getProductName(), order.getTo(), order.getStatus());
    // }
}
