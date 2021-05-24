package com.practice.rabbitmq.publisher;

import com.practice.rabbitmq.service.MessageService;
import com.practice.rabbitmq.service.impl.MessageServiceImpl;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagePublisherConfiguration {
    @Bean
    public TopicExchange messageExchange() {
        return new TopicExchange("messageExchange");
    }

    @Bean
    public MessageService sendMessage(RabbitTemplate rabbitTemplate, TopicExchange messageExchange) {
        return new MessageServiceImpl(rabbitTemplate, messageExchange);
    }
}
