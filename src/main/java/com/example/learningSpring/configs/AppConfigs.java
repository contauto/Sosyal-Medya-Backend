package com.example.learningSpring.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "socio")
public class AppConfigs {
    private String uploadPath;
}
