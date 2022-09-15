package com.example.learningSpring;

import com.example.learningSpring.user.User;
import com.example.learningSpring.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication()
public class LearningSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserService userService){
		return (args) -> {
				User user = new User();
				user.setUsername("user1");
				user.setName("User");
				user.setPassword("P4ssword");
				userService.save(user);
		};

	}

}
