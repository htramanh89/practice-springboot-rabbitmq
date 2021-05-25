package com.practice.rabbitmq.consumer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConsumerConfiguration {
    @Bean
    public TopicExchange receiverExchange() {
        return new TopicExchange("messageExchange");
    }

    @Bean
    public Queue queue() {
        return new Queue("messageServiceQueue");
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange receiverExchange) {
        return BindingBuilder
                .bind(queue)
                .to(receiverExchange)
                .with("send.message");
    }

    @Bean
    public MessageConsumer messageReceiver() {
        return new MessageConsumer();
    }
}
