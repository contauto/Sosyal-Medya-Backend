package com.example.learningSpring.user.Dtos;

import com.example.learningSpring.user.User;
import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String name;
    private  String image;
    public UserDto(User user) {
        this.setUsername(user.getUsername());
        this.setName(user.getName());
        this.setImage(user.getImage());
    }

}
