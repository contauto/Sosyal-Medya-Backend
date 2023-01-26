package com.example.learningSpring.file.Dtos;

import com.example.learningSpring.file.FileAttachment;
import lombok.Data;

@Data
public class FileAttachmentDto {
    private String name;
    private String fileType;

    public FileAttachmentDto(FileAttachment fileAttachment) {
        this.setName(fileAttachment.getName());
        this.fileType = fileAttachment.getFileType();
    }
}
