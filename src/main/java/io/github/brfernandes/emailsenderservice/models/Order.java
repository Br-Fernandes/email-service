package io.github.brfernandes.emailsenderservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String username;
    private String productName;
    private String to;
    private String status;
}
