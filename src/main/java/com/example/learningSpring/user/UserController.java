package com.example.learningSpring.user;

import com.example.learningSpring.error.ApiError;
import com.example.learningSpring.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("/api/1.0/users")
    public ResponseEntity<?> CreateUser(@RequestBody User user){
        String username=user.getUsername();
        if(username==null || username.isEmpty()){
        ApiError apiError=new ApiError(400,"Validation Error","/api/1.0/users");
        Map<String,String> validationErrors=new HashMap<>();
        validationErrors.put("username","username can not be null");
        apiError.setValidationErrors(validationErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
        }
        userService.save(user);
        return ResponseEntity.ok(new GenericResponse("user created"));
    }

}
