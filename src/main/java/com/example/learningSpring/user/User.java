package com.example.learningSpring.user;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    private long id;

    private String username;

    private String name;

    private String password;
}
