package io.github.brfernandes.emailsenderservice.services.impl;

import io.github.brfernandes.emailsenderservice.domains.Confirmation;
import io.github.brfernandes.emailsenderservice.domains.User;
import io.github.brfernandes.emailsenderservice.repositories.ConfirmationRepository;
import io.github.brfernandes.emailsenderservice.repositories.UserRepository;
import io.github.brfernandes.emailsenderservice.services.EmailSenderService;
import io.github.brfernandes.emailsenderservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public User saveUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {throw new RuntimeException("Email already exists!");}

        user.setEnabled(false);
        userRepository.save(user);

        Confirmation confirmation = new Confirmation(user);
        confirmationRepository.save(confirmation);

        //emailService.sendSimpleEmailMessage(user.getName(), user.getEmail(), confirmation.getToken());
        //emailService.sendMineMessageWithAttachment(user.getName(), user.getEmail(), confirmation.getToken());
        //emailService.sendHtmlEmailWithEmbeddedFiles(user.getName(), user.getEmail(), confirmation.getToken());
        emailService.sendHtmlEmail(user.getName(), user.getEmail(), confirmation.getToken());
        return user;
    }

    @Override
    public Boolean verifyToken(String token) {
        Confirmation confirmation = confirmationRepository.findByToken(token);
        User user = userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail());
        user.setEnabled(true);
        userRepository.save(user);
        //confirmationRepository.delete(confirmation);
        return true;
    }
}
