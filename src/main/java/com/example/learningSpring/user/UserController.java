package com.example.learningSpring.user;
import com.example.learningSpring.shared.GenericResponse;
import com.example.learningSpring.user.Dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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

    @GetMapping("/api/1.0/users")
    Page<UserDto> getUsers(Pageable pageable){
        return userService.getUsers(pageable).map(UserDto::new);
    }

}





