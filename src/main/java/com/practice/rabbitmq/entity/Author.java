package com.practice.rabbitmq.entity;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.practice.rabbitmq.entity.deserializer.AuthorDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.persistence.*;

@Getter @Setter @NoArgsConstructor
@Entity
@Table (name = "author")
@JsonDeserialize(using = AuthorDeserializer.class)
public class Author {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "active")
    private boolean isActive;

    public Author (String userName, String password, boolean isActive) {
        this.userName = userName;
        this.password = password;
        this.isActive = isActive;
    }

    @SneakyThrows
    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
