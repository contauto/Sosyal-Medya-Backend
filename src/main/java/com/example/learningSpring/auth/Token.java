package com.example.learningSpring.auth;

import com.example.learningSpring.user.User;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Token {
    @Id
    private String token;
    @ManyToOne
    private User user;
}
