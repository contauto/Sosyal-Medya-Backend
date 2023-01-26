package com.example.learningSpring.file;

import com.example.learningSpring.configs.AppConfigs;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@EnableScheduling
public class FileService {

    AppConfigs appConfigs;
    Tika tika;
    FileAttachmentRepository fileAttachmentRepository;

    public FileService(AppConfigs appConfigs, FileAttachmentRepository fileAttachmentRepository) {
        super();
        this.appConfigs = appConfigs;
        this.tika = new Tika();
        this.fileAttachmentRepository = fileAttachmentRepository;
    }


    public String writeBase64EncodedStringToFile(String image) throws IOException {
        String fileName = generateRandomName();
        File file = new File(appConfigs.getProfilePhotoStoragePath());
        OutputStream outputStream = new FileOutputStream(file);
        byte[] base64Decoded = Base64.getDecoder().decode(image);
        outputStream.write(base64Decoded);
        outputStream.close();
        return fileName;

    }

    public String generateRandomName() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public void deleteProfilePhoto(String oldImageName) {
        if (oldImageName == null) {
            return;
        }
        deleteFile(Paths.get(appConfigs.getProfilePhotoStoragePath(), oldImageName));
    }

    public void deleteAttachmentFile(String oldFileName) {
        if (oldFileName == null) {
            return;
        }
        deleteFile(Paths.get(appConfigs.getAttachmentStoragePath(), oldFileName));
    }

    private void deleteFile(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String detectType(byte[] array) {
        return tika.detect(array);
    }

    public String detectType(String value) {
        byte[] base64Decoded = Base64.getDecoder().decode(value);
        return tika.detect(base64Decoded);
    }

    public FileAttachment saveSosAttachment(MultipartFile multipartFile) {
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        String fileName = generateRandomName() + "." + extension;
        File target = new File(appConfigs.getAttachmentStoragePath() + "/" + fileName);
        String fileType = null;
        try {
            byte[] array = multipartFile.getBytes();
            OutputStream outputStream = new FileOutputStream(target);
            outputStream.write(array);
            outputStream.close();
            fileType = detectType(array);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileAttachment fileAttachment = new FileAttachment();
        fileAttachment.setName(fileName);
        fileAttachment.setDate(new Date());
        fileAttachment.setFileType(fileType);
        return fileAttachmentRepository.save(fileAttachment);
    }

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void cleanUpStorage() {
        Date twentyFourHourAgo = new Date(System.currentTimeMillis() - (24 * 60 * 60 * 1000));
        List<FileAttachment> filesToBeDeleted = fileAttachmentRepository.findByDateBeforeAndSosIsNull(twentyFourHourAgo);
        for (FileAttachment fileAttachment : filesToBeDeleted) {
            deleteAttachmentFile(fileAttachment.getName());
            fileAttachmentRepository.deleteById(fileAttachment.getId());
        }
    }
}