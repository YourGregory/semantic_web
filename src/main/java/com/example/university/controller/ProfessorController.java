package com.example.university.controller;

import com.example.university.dto.request.ProfessorRequest;
import com.example.university.model.Professor;
import com.example.university.service.ProfessorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/professor")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProfessorController {

    ProfessorService service;

    @GetMapping
    public ResponseEntity<List<Professor>> getAllProfessors() {
        return ResponseEntity.ok(service.getAllProfessors());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Professor> createProfessor(@RequestBody ProfessorRequest request) {
        return ResponseEntity.ok(service.createProfessor(request));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable Long id, @RequestBody ProfessorRequest request) {
        return ResponseEntity.ok(service.updateProfessor(id, request));
    }

    @DeleteMapping
    public ResponseEntity<Long> deleteProfessor(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteProfessor(id));
    }
}
