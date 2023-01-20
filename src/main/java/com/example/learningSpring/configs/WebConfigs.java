package com.example.learningSpring.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfigs implements WebMvcConfigurer {
    @Autowired
    AppConfigs appConfigs;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:./" + appConfigs.getUploadPath() + "/").setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
    }

    @Bean
    CommandLineRunner createStorageDirectories() {
        return args -> {
            File folder = new File(appConfigs.getUploadPath());
            boolean folderExist = folder.exists() && folder.isDirectory();
            if (!folderExist) {
                folder.mkdir();
            }
        };
    }
}