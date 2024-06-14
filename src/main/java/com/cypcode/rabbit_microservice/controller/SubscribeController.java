package com.cypcode.rabbit_microservice.controller;

import com.cypcode.rabbit_microservice.domain.SubscriberDTO;
import com.cypcode.rabbit_microservice.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("subscribe")
public class SubscribeController {
    @Autowired
    private PublisherService publisherService;

    @PostMapping
    public ResponseEntity<?> home(@RequestBody SubscriberDTO dto){
    	System.out.println("Publish Data: "+ dto.getName());
        publisherService.publish(dto);
        return ResponseEntity.ok().build();
    }
}
