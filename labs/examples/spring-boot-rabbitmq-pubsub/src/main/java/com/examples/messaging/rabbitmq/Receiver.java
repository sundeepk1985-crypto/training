package com.examples.messaging.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues="incident_create")
public class Receiver {

	@RabbitHandler
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
    }
}