package io.github.brfernandes.emailsenderservice.controllers;

import io.github.brfernandes.emailsenderservice.models.HttpResponse;
import io.github.brfernandes.emailsenderservice.models.Order;
import io.github.brfernandes.emailsenderservice.models.User;
import io.github.brfernandes.emailsenderservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<HttpResponse> sendWelcomeEmail(@RequestBody User user) {
        userService.sendWelcomeEmail(user);
        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .message("User created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @PostMapping("/new-order")
    public ResponseEntity<HttpResponse> sendConfirmedOrder(@RequestBody Order order) {
        userService.sendConfirmedOrder(order);
        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .message("Order created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<HttpResponse> confirmUserAccount(@RequestParam("token") String token) {
        Boolean isSuccess = userService.verifyToken(token);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("Success", isSuccess))
                        .message("Account verified")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
