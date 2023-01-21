package com.example.learningSpring.sos;

import com.example.learningSpring.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SosController {
    @Autowired
    SosService sosService;

    @PostMapping("/api/1.0/sosses")
    GenericResponse saveSos(@Valid @RequestBody Sos sos) {
        sosService.save(sos);
        return new GenericResponse("Sos is saved");
    }
}
