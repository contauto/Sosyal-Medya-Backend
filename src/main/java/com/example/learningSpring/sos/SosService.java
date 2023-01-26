package com.example.learningSpring.sos;

import com.example.learningSpring.file.FileAttachment;
import com.example.learningSpring.file.FileAttachmentRepository;
import com.example.learningSpring.sos.Dtos.SosSubmitDto;
import com.example.learningSpring.user.User;
import com.example.learningSpring.user.UserRepository;
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
    UserService userService;
    FileAttachmentRepository fileAttachmentRepository;
    private final UserRepository userRepository;

    public SosService(SosRepository sosRepository, UserService userService,
                      UserRepository userRepository, FileAttachmentRepository fileAttachmentRepository) {
        this.sosRepository = sosRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.fileAttachmentRepository = fileAttachmentRepository;
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

    Specification<Sos> findByIdGreaterThan(long id) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThan(root.get("id"), id);
        };
    }

    Specification<Sos> findByIdLessThan(long id) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThan(root.get("id"), id);
        };

    }

    Specification<Sos> findByUser(User user) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("user"), user);
        };
    }

}
