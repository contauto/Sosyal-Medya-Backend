package com.example.learningSpring.user;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String username;

    @NotNull
    private String name;

    @NotNull
    private String password;
}
