
package io.github.brfernandes.emailsenderservice.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
    
    @KafkaListener(topics = "new-user-topic", groupId="email-user-consumer")
    public void sendWelcomerEmail(User user){
        Confirmation confirmation = new Confirmation();
        emailService.sendWelcomeEmail(user.getName(), user.getEmail(), confirmation.getToken());
    }

    @KafkaListener(topics = "new-order-topic", groupId = "email-order-consumer")
    public void send(String message) {
        ObjectMapper mapper = new ObjectMapper();
        Order order;
        try {
            order = mapper.readValue(message, Order.class);
            emailService.sendConfirmedOrderEmail(order.getUsername(), order.getProductName(), order.getTo(), order.getStatus());

        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
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
