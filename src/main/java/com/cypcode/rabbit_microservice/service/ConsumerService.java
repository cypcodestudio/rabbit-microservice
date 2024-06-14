package com.cypcode.rabbit_microservice.service;

import com.cypcode.rabbit_microservice.domain.SubscriberDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    @RabbitListener(queues = "${rabbit.queue.name}")
    public void processMessage(String payload) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            SubscriberDTO dto = mapper.readValue(payload, SubscriberDTO.class);
            System.out.println(String.format("Consuming Queue Data: Id %s, Name: %s ", dto.getId(), dto.getName()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
