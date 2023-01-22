package com.example.learningSpring.sos;

import com.example.learningSpring.shared.CurrentUser;
import com.example.learningSpring.shared.GenericResponse;
import com.example.learningSpring.sos.Dtos.SosDto;
import com.example.learningSpring.user.User;
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
    GenericResponse saveSos(@Valid @RequestBody Sos sos, @CurrentUser User user) {
        sosService.save(sos, user);
        return new GenericResponse("Sos is saved");
    }

    @GetMapping("/sosses")
    Page<SosDto> getSosses(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return sosService.getSosses(pageable).map(SosDto::new);
    }

    @GetMapping("/users/{username}/sosses")
    Page<SosDto> getUserSosses(@PathVariable String username, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return sosService.getSossesFromUser(username, pageable).map(SosDto::new);
    }

    @GetMapping("/sosses/{id:[0-9]+}")
    Page<SosDto> getSossesRelative(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable, @PathVariable long id) {
        return sosService.getOldSosses(id, pageable).map(SosDto::new);
    }

    @GetMapping("/users/{username}/sosses/{id:[0-9]+}")
    Page<SosDto> getUserSossesRelative(@PathVariable long id, @PathVariable String username, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return sosService.getOldSossesOfUser(id, username, pageable).map(SosDto::new);
    }
}
