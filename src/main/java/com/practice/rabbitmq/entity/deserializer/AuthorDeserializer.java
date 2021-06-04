package com.practice.rabbitmq.entity.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.practice.rabbitmq.entity.Author;

import java.io.IOException;

public class AuthorDeserializer extends StdDeserializer<Author> {
    protected AuthorDeserializer(Class<?> vc) {
        super(vc);
    }

    protected AuthorDeserializer(JavaType valueType) {
        super(valueType);
    }

    protected AuthorDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public Author deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Author author = new Author();
        JsonNode node = p.getCodec().readTree(p);
        author.setUserName(node.get("userName").asText());
        author.setPassword(node.get("password").asText());
        Boolean isActive = node.get("active") == null ? false : node.get("active").isBoolean();
        author.setActive(isActive);
        if(node.get("id") != null) {
            author.setId((node.get("id")).numberValue().longValue());
        }
        return author;
    }
}
