package com.example.learningSpring.file;

import com.example.learningSpring.configs.AppConfigs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileService {
    @Autowired
    AppConfigs appConfigs;

    public String writeBase64EncodedStringToFile(String image) throws IOException {
        String fileName = generateRandomName();
        File file = new File(appConfigs.getUploadPath() + "/" + fileName);
        OutputStream outputStream = new FileOutputStream(file);
        byte[] base64Decoded = Base64.getDecoder().decode(image);
        outputStream.write(base64Decoded);
        outputStream.close();
        return fileName;

    }

    public String generateRandomName() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public void deleteFile(String oldImageName) {
        if (oldImageName == null) {
            return;
        }
        try {
            Files.deleteIfExists(Paths.get(appConfigs.getUploadPath(), oldImageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
