package com.practice.rabbitmq.entity.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.practice.rabbitmq.entity.Event;

import java.io.IOException;

public class EventDeserializer extends StdDeserializer<Event> {

    protected EventDeserializer(Class<?> vc) {
        super(vc);
    }

    protected EventDeserializer(JavaType valueType) {
        super(valueType);
    }

    protected EventDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public Event deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        Long authorId = (node.get("authorId")).numberValue().longValue();
        return new Event();
    }
}
