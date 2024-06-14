package com.cypcode.rabbit_microservice.service;

import com.cypcode.rabbit_microservice.domain.SubscriberDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {
    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private AmqpTemplate amqpTemplate;


    @Value("${rabbit.queue.name}")
    private String queueName;
    @Value("${rabbit.queue.exchange}")
    private String exchangeName;
    @Value("${rabbit.queue.route-key}")
    private String routingKey;

    public void publish(SubscriberDTO data) {
        ObjectMapper mapper = new ObjectMapper();
        String payload = null;
        try {
            payload = mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        amqpTemplate.convertAndSend(exchangeName, routingKey, payload);
    }
}
