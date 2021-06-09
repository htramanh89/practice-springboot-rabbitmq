package com.practice.rabbitmq.entity.deserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.practice.rabbitmq.entity.Author;
import com.practice.rabbitmq.entity.Message;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class MessageDeserializer extends StdDeserializer<Message> {
    public MessageDeserializer() {
        this(null);
    }
    public MessageDeserializer(Class<?> vc) {
        super(vc);
    }


    @Override
    public Message deserialize(JsonParser p, DeserializationContext ctxt) throws IOException{
        Message message = new Message();
        JsonNode node = p.getCodec().readTree(p);
        message.setContent(node.get("content").asText());
        Timestamp createdAt = node.get("createdAt") == null ? Timestamp.valueOf(LocalDateTime.now())
                : new Timestamp(node.get("createdAt").longValue());
        Author author = new Author();
        message.setCreatedAt(createdAt);
        if(node.get("id") != null) {
            message.setId(node.get("id").numberValue().longValue());
            author.setId(node.get("author").get("id").numberValue().longValue());
            author.setUserName(node.get("author").get("userName").asText());
        } else {
            Long authorId = node.get("authorId").numberValue().longValue();
            author.setId(authorId);
        }
        message.setAuthor(author);
        return message;
    }
}
