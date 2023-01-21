package com.example.learningSpring.sos;

import com.example.learningSpring.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/1.0")
public class SosController {
    @Autowired
    SosService sosService;

    @PostMapping("/sosses")
    GenericResponse saveSos(@Valid @RequestBody Sos sos) {
        sosService.save(sos);
        return new GenericResponse("Sos is saved");
    }

    @GetMapping("/sosses")
    Page<Sos> getSosses(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return sosService.getSosses(pageable);
    }
}
