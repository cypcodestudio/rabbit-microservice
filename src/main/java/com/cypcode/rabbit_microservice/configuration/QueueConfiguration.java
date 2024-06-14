package com.cypcode.rabbit_microservice.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class QueueConfiguration {
    @Value("${rabbit.queue.name}")
    private String queueName;
    @Value("${rabbit.queue.exchange}")
    private String exchangeName;
    @Value("${rabbit.queue.route-key}")
    private String routingKey;
    @Value("${spring.rabbitmq.port}")
    private int queuePort;


    @Bean
    public Queue queue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "cypcode-subscriber-queue-dlx");
        return new Queue(queueName, true, false, false, args);
    }

    @Bean
    Exchange myExchange() {
        return ExchangeBuilder.topicExchange(exchangeName)
                .durable(true)
                .autoDelete()
                .build();
    }

    @Bean
    Declarables bindings(TopicExchange exchange, Queue queue) {
        return new Declarables(BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(routingKey));
    }
}
