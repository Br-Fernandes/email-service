package io.github.brfernandes.emailsenderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableAsync(proxyTargetClass = true)
public class EmailSenderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailSenderServiceApplication.class, args);
    }

}
