package com.example.learningSpring.sos;

import com.example.learningSpring.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "sosSecurity")
public class SosSecurityService {
    @Autowired
    SosRepository sosRepository;

    public boolean isAllowedToDelete(long id, User user) {
        Optional<Sos> optionalSos = sosRepository.findById(id);
        if (!optionalSos.isPresent()) {
            return false;
        }
        Sos sos = optionalSos.get();
        if (sos.getUser().getId() != user.getId()) {
            return false;
        }
        return true;
    }
}
