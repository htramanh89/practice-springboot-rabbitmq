package com.practice.rabbitmq.entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.sql.Timestamp;

public class MessageDeserializer extends StdDeserializer<Message> {
    public MessageDeserializer() {
        this(null);
    }
    public MessageDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Message deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        Long id =  (node.get("id")).numberValue().longValue();
        Long authorId = (node.get("authorId")).numberValue().longValue();
        String content = node.get("content").asText();
        Timestamp createdAt = Timestamp.valueOf(node.get("createdAt").asText());

        return new Message(id, authorId, content, createdAt);
    }
}
