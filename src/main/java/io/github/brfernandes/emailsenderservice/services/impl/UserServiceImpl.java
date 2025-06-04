package io.github.brfernandes.emailsenderservice.services.impl;

import org.springframework.stereotype.Service;

import io.github.brfernandes.emailsenderservice.models.Confirmation;
import io.github.brfernandes.emailsenderservice.models.Order;
import io.github.brfernandes.emailsenderservice.models.User;
import io.github.brfernandes.emailsenderservice.repositories.ConfirmationRepository;
import io.github.brfernandes.emailsenderservice.repositories.UserRepository;
import io.github.brfernandes.emailsenderservice.services.EmailSenderService;
import io.github.brfernandes.emailsenderservice.services.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ConfirmationRepository confirmationRepository;
    private final EmailServiceImpl emailService;
    private final EmailSenderService emailSenderService;


    @Override
    public void sendWelcomeEmail(User user) {
        Confirmation confirmation = new Confirmation(user);
        //confirmationRepository.save(confirmation);

        emailSenderService.sendWelcomeEmail(user.getName(), user.getEmail(), confirmation.getToken());
    }

    @Override
    public void sendConfirmedOrder(Order order) {
        // emailSenderService.sendConfirmedOrderEmail(
        //         // order.getUser().getName(),
        //         // order.getName(),
        //         // order.getUser().getEmail(),
        //         // order.getStatus()
        // );
    }

    @Override
    public Boolean verifyToken(String token) {
        Confirmation confirmation = confirmationRepository.findByToken(token);
        User user = userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail());
        userRepository.save(user);
        //confirmationRepository.delete(confirmation);
        return true;
    }
}
