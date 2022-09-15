package com.example.learningSpring.user;

import com.example.learningSpring.error.ApiError;
import com.example.learningSpring.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("/api/1.0/users")
    public GenericResponse CreateUser(@Valid @RequestBody User user) {


        userService.save(user);
        return new GenericResponse("user created");
    }
}





