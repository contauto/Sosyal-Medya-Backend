package com.example.learningSpring.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/1.0")
public class FileController {
    @Autowired
    FileService fileService;

    @PostMapping("/sos-attachments")
    FileAttachment saveSosAttachment(MultipartFile file) {
        return fileService.saveSosAttachment(file);
    }
}
