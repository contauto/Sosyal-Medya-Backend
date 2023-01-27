package com.example.learningSpring.file;

import com.example.learningSpring.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface FileAttachmentRepository extends JpaRepository<FileAttachment, Long> {
    List<FileAttachment> findByDateBeforeAndSosIsNull(Date date);

    List<FileAttachment> findBySosUser(User user);
}
