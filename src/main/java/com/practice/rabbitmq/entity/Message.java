package com.practice.rabbitmq.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "message")
@JsonDeserialize(using = MessageDeserializer.class)
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public Message (Long id, Long authorId, String content, Timestamp createdAt) {
        this.id = id;
        this.authorId = authorId;
        this.content = content;
        this.createdAt = createdAt;
    }

    @Override
    @JsonValue
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"authorId\":" + authorId +
                ", \"content\":\"" + content + "\"" +
                ", \"createdAt\":\"" + createdAt + "\"" +
                "}";
    }

}
