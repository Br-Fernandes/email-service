package io.github.brfernandes.emailsenderservice.services.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import io.github.brfernandes.emailsenderservice.services.EmailSenderService;
import static io.github.brfernandes.emailsenderservice.utils.EmailUtils.getVerificationUrl;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {

    public static final String NEW_USER_ACCOUNT_VERIFICATION = "Verificação de Conta de Novo Usuário";
    public static final String ORDER_CONFIRMED = "Pedido Confirmado!";
    public static final String UTF_8_ENCODING = "UTF-8";
    public static final String WELCOME_TEMPLATE = "WelcomeTemplate";
    public static final String ORDER_TEMPLATE = "OrderTemplate";
    public static final String TEXT_HTML_ENCONDING = "text/html";

    @Value("${spring.mail.verify.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    @Override
    public void sendSimpleEmailMessage(String name, String to, String token) {

    }

    @Override
    public void sendWelcomeEmail(String name, String to,  String token) {
        System.out.println(getVerificationUrl(host, token));
        try {
            Context context = new Context();
            context.setVariables(Map.of("name", name, "url", getVerificationUrl(host ,token)));
            createHtmlEmail(to, context, WELCOME_TEMPLATE, NEW_USER_ACCOUNT_VERIFICATION);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void sendConfirmedOrderEmail(String username, String productName,String to, String status) {
        try {
            Context context = new Context();
            context.setVariables(Map.of("userName", username,"productName", productName, "status" ,status));
            createHtmlEmail(to, context, ORDER_TEMPLATE, ORDER_CONFIRMED);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void createHtmlEmail(String to, Context context, String orderTemplate, String orderConfirmed) throws MessagingException {
        String text = templateEngine.process(orderTemplate, context);
        MimeMessage message = getMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
        helper.setPriority(1);
        helper.setSubject(orderConfirmed);
        helper.setFrom(fromEmail);
        helper.setTo(to);
        helper.setText(text, true);

        emailSender.send(message);
    }

    private MimeMessage getMimeMessage() {
        return emailSender.createMimeMessage();
    }
}
