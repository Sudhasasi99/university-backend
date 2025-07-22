package com.university.university_backend.controller;

import com.university.university_backend.entity.HOD;
import com.university.university_backend.service.HODService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hods")
public class HODController {
    @Autowired
    private HODService hodService;

    @GetMapping
    public List<HOD> getAllHODs() {
        return hodService.getAllHODs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HOD> getHODById(@PathVariable Long id) {
        Optional<HOD> hod = hodService.getHODById(id);
        return hod.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public HOD createHOD(@RequestBody HOD hod) {
        return hodService.createHOD(hod);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HOD> updateHOD(@PathVariable Long id, @RequestBody HOD hodDetails) {
        HOD updatedHOD = hodService.updateHOD(id, hodDetails);
        if (updatedHOD == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedHOD);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHOD(@PathVariable Long id) {
        hodService.deleteHOD(id);
        return ResponseEntity.noContent().build();
    }
} 