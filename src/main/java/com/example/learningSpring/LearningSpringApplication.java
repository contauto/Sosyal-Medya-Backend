package com.example.learningSpring;

import com.example.learningSpring.user.User;
import com.example.learningSpring.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication()
public class LearningSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningSpringApplication.class, args);
    }

    @Bean
    @Profile("dev")
    CommandLineRunner commandLineRunner(UserService userService) {
        return (args) -> {
            for (int i = 0; i < 10; i++) {

                User user = new User();
                user.setUsername("user" + i);
                user.setName("name" + i);
                user.setPassword("P4ssword");
                userService.save(user);
            }
        };

    }

}
