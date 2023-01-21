package com.example.learningSpring.sos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SosService {
    SosRepository sosRepository;

    public SosService(SosRepository sosRepository) {
        this.sosRepository = sosRepository;
    }

    public void save(Sos sos) {
        sos.setTimestamp(new Date());
        sosRepository.save(sos);
    }

    public Page<Sos> getSosses(Pageable pageable) {
        return sosRepository.findAll(pageable);
    }
}
