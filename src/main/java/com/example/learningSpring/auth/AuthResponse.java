package com.example.learningSpring.auth;

import com.example.learningSpring.user.Dtos.UserDto;
import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private UserDto userDto;
}
