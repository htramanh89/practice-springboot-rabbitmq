package com.practice.rabbitmq.producer;

import com.practice.rabbitmq.service.MessageService;
import com.practice.rabbitmq.service.impl.MessageServiceImpl;
import org.springframework.amqp.core.Exchange;
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
        return new MessageServiceImpl(rabbitTemplate, senderExchange);
    }
}
