package com.example.learningSpring.auth;


import com.example.learningSpring.shared.CurrentUser;
import com.example.learningSpring.user.Dtos.UserDto;
import com.example.learningSpring.user.User;
import com.example.learningSpring.user.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/api/1.0/auth")
    UserDto HandleAuthentication(@CurrentUser User user) {
        return new UserDto(user);
    }

}
