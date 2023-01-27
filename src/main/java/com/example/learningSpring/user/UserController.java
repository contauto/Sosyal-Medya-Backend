package com.example.learningSpring.user;

import com.example.learningSpring.shared.CurrentUser;
import com.example.learningSpring.shared.GenericResponse;
import com.example.learningSpring.user.Dtos.UserDto;
import com.example.learningSpring.user.Dtos.UserUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/1.0")
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("/users")
    public GenericResponse CreateUser(@Valid @RequestBody User user) {


        userService.save(user);
        return new GenericResponse("user created");
    }

    @GetMapping("/users")
    Page<UserDto> getUsers(@CurrentUser User user, Pageable pageable) {
        return userService.getUsers(user, pageable).map(UserDto::new);
    }

    @GetMapping("/users/{username}")
    UserDto getUser(@PathVariable String username) {
        User user = userService.getByUsername(username);
        return new UserDto(user);
    }

    @PutMapping("/users/{username}")
    @PreAuthorize("#username==principal.username")
    UserDto updateUser(@Valid @RequestBody UserUpdateDto userUpdateDto, @PathVariable String username) {
        User user = userService.updateUser(username, userUpdateDto);
        return new UserDto(user);
    }

    @DeleteMapping("/users/{username}")
    @PreAuthorize("#username==principal.username")
    GenericResponse deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return new GenericResponse("User is removed");
    }

}





