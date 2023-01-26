package com.example.learningSpring.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "socio")
public class AppConfigs {
    private String uploadPath;
    private String profilePhotoStorage = "profile-photo";
    private String attachmentStorage = "attachments";

    public String getAttachmentStoragePath() {
        return uploadPath + "/" + attachmentStorage;
    }

    public String getProfilePhotoStoragePath() {
        return uploadPath + "/" + profilePhotoStorage;
    }
}
