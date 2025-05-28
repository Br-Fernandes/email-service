package io.github.brfernandes.emailsenderservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {

    private String name;
    private String status;
    private User user;
}
