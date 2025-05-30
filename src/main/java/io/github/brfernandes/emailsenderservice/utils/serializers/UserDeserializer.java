package io.github.brfernandes.emailsenderservice.utils.serializers;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.brfernandes.emailsenderservice.models.User;

public class UserDeserializer implements Deserializer<User>{

    @Override
    public User deserialize(String topic, byte[] data) {
        try {
            return new ObjectMapper().readValue(data, User.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}