package com.practice.rabbitmq.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.practice.rabbitmq.entity.deserializer.EventDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonDeserialize(using = EventDeserializer.class)
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

   /* @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "message_id")
    private Message message;*/

    @SneakyThrows
    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
