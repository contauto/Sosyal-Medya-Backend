package com.example.learningSpring.sos;

import com.example.learningSpring.user.User;
import com.example.learningSpring.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SosService {
    SosRepository sosRepository;
    UserService userService;

    public SosService(SosRepository sosRepository, UserService userService) {
        this.sosRepository = sosRepository;
        this.userService = userService;
    }

    public void save(Sos sos, User user) {
        sos.setTimestamp(new Date());
        sos.setUser(user);
        sosRepository.save(sos);
    }

    public Page<Sos> getSosses(Pageable pageable) {
        return sosRepository.findAll(pageable);
    }

    public Page<Sos> getSossesFromUser(String username, Pageable pageable) {
        User inDB = userService.getByUsername(username);
        return sosRepository.findByUser(inDB, pageable);
    }

    public Page<Sos> getOldSosses(long id, Pageable pageable) {
        return sosRepository.findByIdLessThan(id, pageable);
    }

    public Page<Sos> getOldSossesOfUser(long id, String username, Pageable pageable) {
        User inDB = userService.getByUsername(username);
        return sosRepository.findByIdLessThanAndUser(id, pageable, inDB);
    }

    public long getNewSossesCount(long id) {
        return sosRepository.countByIdGreaterThan(id);
    }
}
