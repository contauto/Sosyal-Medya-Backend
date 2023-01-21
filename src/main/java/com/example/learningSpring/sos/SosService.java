package com.example.learningSpring.sos;

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
}
