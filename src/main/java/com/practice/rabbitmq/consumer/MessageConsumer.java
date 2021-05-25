package com.practice.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class MessageConsumer {
    @RabbitListener(queues="messageServiceQueue")
    public void receive(String message) {
        System.out.println("Received message: " + message);
    }
}
