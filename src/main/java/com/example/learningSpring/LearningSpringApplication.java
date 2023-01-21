package com.example.learningSpring;

import com.example.learningSpring.sos.Sos;
import com.example.learningSpring.sos.SosService;
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
    CommandLineRunner commandLineRunner(UserService userService, SosService sosService) {
        return (args) -> {
            for (int i = 0; i < 10; i++) {

                User user = new User();
                user.setUsername("user" + i);
                user.setName("name" + i);
                user.setPassword("P4ssword");
                userService.save(user);
            }

            for (int i = 0; i < 50; i++) {
                Sos sos = new Sos();
                sos.setContent("sos-" + i);
                sosService.save(sos);

            }
        };

    }

}
