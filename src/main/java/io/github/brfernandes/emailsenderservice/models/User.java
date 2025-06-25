package io.github.brfernandes.emailsenderservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class User {

    private String name;
    private String email;
    private String password;
    private String address;
    private String token;
}
