package io.github.brfernandes.emailsenderservice.utils.serializers;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.brfernandes.emailsenderservice.models.Order;

public class OrderDeserializer implements Deserializer<Order> {

    @Override
    public Order deserialize(String topic, byte[] data) {
        try {
            return new ObjectMapper().readValue(data, Order.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
