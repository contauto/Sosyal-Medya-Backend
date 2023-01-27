package com.example.learningSpring.sos;

import com.example.learningSpring.file.FileAttachment;
import com.example.learningSpring.file.FileAttachmentRepository;
import com.example.learningSpring.file.FileService;
import com.example.learningSpring.sos.Dtos.SosSubmitDto;
import com.example.learningSpring.user.User;
import com.example.learningSpring.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SosService {
    SosRepository sosRepository;
    FileAttachmentRepository fileAttachmentRepository;
    FileService fileService;
    UserService userService;

    public SosService(SosRepository sosRepository, FileAttachmentRepository fileAttachmentRepository, FileService fileService, UserService userService) {
        this.sosRepository = sosRepository;
        this.fileAttachmentRepository = fileAttachmentRepository;
        this.fileService = fileService;
        this.userService = userService;
    }


    public void save(SosSubmitDto sosSubmitDto, User user) {
        Sos sos = new Sos();
        sos.setContent(sosSubmitDto.getContent());
        sos.setTimestamp(new Date());
        sos.setUser(user);
        sosRepository.save(sos);
        Optional<FileAttachment> optionalFileAttachment = fileAttachmentRepository.findById(sosSubmitDto.getAttachmentId());
        if (optionalFileAttachment.isPresent()) {
            FileAttachment fileAttachment = optionalFileAttachment.get();
            fileAttachment.setSos(sos);
            fileAttachmentRepository.save(fileAttachment);
        }
    }

    public Page<Sos> getSosses(Pageable pageable) {
        return sosRepository.findAll(pageable);
    }

    public Page<Sos> getSossesFromUser(String username, Pageable pageable) {
        User inDB = userService.getByUsername(username);
        return sosRepository.findByUser(inDB, pageable);
    }

    public Page<Sos> getOldSosses(long id, String username, Pageable pageable) {
        Specification<Sos> sosSpecification = findByIdLessThan(id);
        if (username != null) {
            User inDB = userService.getByUsername(username);
            sosSpecification = sosSpecification.and(findByUser(inDB));
        }
        return sosRepository.findAll(sosSpecification, pageable);
    }

    public long getNewSossesCount(long id, String username) {
        Specification<Sos> sosSpecification = findByIdGreaterThan(id);
        if (username != null) {
            User inDB = userService.getByUsername(username);
            sosSpecification = sosSpecification.and(findByUser(inDB));
        }
        return sosRepository.count(sosSpecification);
    }

    public List<Sos> getNewSosses(long id, Sort sort, String username) {
        Specification<Sos> sosSpecification = findByIdGreaterThan(id);
        if (username != null) {
            User inDB = userService.getByUsername(username);
            sosSpecification = sosSpecification.and(findByUser(inDB));
        }
        return sosRepository.findAll(sosSpecification, sort);
    }

    public void delete(long id) {

        Sos inDB = sosRepository.getReferenceById(id);
        if (inDB.getFileAttachment() != null) {
            String fileName = inDB.getFileAttachment().getName();
            fileService.deleteAttachmentFile(fileName);
        }
        sosRepository.deleteById(id);
    }

    Specification<Sos> findByIdGreaterThan(long id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("id"), id);
    }

    Specification<Sos> findByIdLessThan(long id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("id"), id);

    }

    Specification<Sos> findByUser(User user) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("user"), user);
    }


}
