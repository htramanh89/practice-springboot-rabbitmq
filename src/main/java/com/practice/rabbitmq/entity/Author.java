package com.practice.rabbitmq.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter @NoArgsConstructor
@Entity
@Table (name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String passWord;

    @Column(name = "active")
    private boolean isActive;

    public Author (String userName, String passWord, boolean isActive) {
        this.userName = userName;
        this.passWord = passWord;
        this.isActive = isActive;
    }
}
