package com.example.learningSpring.user;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    private long id;


    @NotNull
    @Size(min = 3,max = 48)
    @UniqueUsername
    private String username;

    @Size(min = 2,max = 64)
    @NotNull
    private String name;

    @Size(min = 8,max = 64)
    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
    @NotNull
    private String password;
}
