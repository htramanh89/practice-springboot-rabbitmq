package com.practice.rabbitmq.service.producer;

import com.practice.rabbitmq.service.MessageService;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageProducerConfiguration {
    @Bean
    public TopicExchange senderExchange() {
        return new TopicExchange("messageExchange");
    }

    @Bean
    public MessageService sendMessage(RabbitTemplate rabbitTemplate, TopicExchange senderExchange) {
        return new MessageService(rabbitTemplate, senderExchange);
    }
}
