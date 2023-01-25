package com.example.learningSpring.file;

import com.example.learningSpring.configs.AppConfigs;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    AppConfigs appConfigs;
    Tika tika;

    public FileService(AppConfigs appConfigs) {
        super();
        this.appConfigs = appConfigs;
        this.tika = new Tika();
    }


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

    public String detectType(String value) {
        byte[] base64Decoded = Base64.getDecoder().decode(value);
        return tika.detect(base64Decoded);
    }

    public String saveSosAttachment(MultipartFile multipartFile) {
        String fileName = generateRandomName();
        File target = new File(appConfigs.getUploadPath() + "/" + fileName);
        try {
            OutputStream outputStream = new FileOutputStream(target);
            outputStream.write(multipartFile.getBytes());
            outputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileName;
    }
}