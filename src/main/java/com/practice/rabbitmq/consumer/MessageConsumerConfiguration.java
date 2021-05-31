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
    public Queue queueGetRequest() {
        return new Queue("messageGetServiceQueue");
    }

    @Bean
    public Queue queuePostRequest() {
        return new Queue("messagePostServiceQueue");
    }

    @Bean
    public Binding bindingGet(Queue queueGetRequest, TopicExchange receiverExchange) {
        return BindingBuilder
                .bind(queueGetRequest)
                .to(receiverExchange)
                .with("message.*");
    }

    @Bean
    public Binding bindingPost(Queue queuePostRequest, TopicExchange receiverExchange) {
        return BindingBuilder
                .bind(queuePostRequest)
                .to(receiverExchange)
                .with("message.*");
    }

    @Bean
    public MessageConsumer messageReceiver() {
        return new MessageConsumer();
    }
}
