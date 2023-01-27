package com.example.learningSpring.sos;

import com.example.learningSpring.shared.CurrentUser;
import com.example.learningSpring.shared.GenericResponse;
import com.example.learningSpring.sos.Dtos.SosDto;
import com.example.learningSpring.sos.Dtos.SosSubmitDto;
import com.example.learningSpring.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/1.0")
public class SosController {
    @Autowired
    SosService sosService;

    @PostMapping("/sosses")
    GenericResponse saveSos(@Valid @RequestBody SosSubmitDto sosSubmitDto, @CurrentUser User user) {
        sosService.save(sosSubmitDto, user);
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

    @GetMapping({"/sosses/{id:[0-9]+}", "/users/{username}/sosses/{id:[0-9]+}"})
    ResponseEntity<?> getSossesRelative(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            @PathVariable long id,
            @PathVariable(required = false) String username,
            @RequestParam(name = "count", required = false, defaultValue = "false") boolean count,
            @RequestParam(name = "direction", defaultValue = "before") String direction) {
        if (count) {
            long newSosCount = sosService.getNewSossesCount(id, username);
            Map<String, Long> response = new HashMap<>();
            response.put("count", newSosCount);
            return ResponseEntity.ok(response);
        }

        if (direction.equals("after")) {
            List<SosDto> newSosses = sosService.getNewSosses(id, pageable.getSort(), username).stream().map(SosDto::new).collect(Collectors.toList());
            return ResponseEntity.ok(newSosses);
        }

        return ResponseEntity.ok(sosService.getOldSosses(id, username, pageable).map(SosDto::new));
    }

    @DeleteMapping("/sosses/{id:[0-9]+}")
    @PreAuthorize("@sosSecurity.isAllowedToDelete(#id,principal)")
    GenericResponse deleteSos(@PathVariable long id) {
        sosService.delete(id);
        return new GenericResponse("Sos removed");
    }


}
