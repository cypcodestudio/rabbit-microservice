package com.cypcode.rabbit_microservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class RabbitMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitMicroserviceApplication.class, args);
	}

}
