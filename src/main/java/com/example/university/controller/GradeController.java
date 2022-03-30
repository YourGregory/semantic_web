package com.example.university.controller;

import com.example.university.dto.GradeDTO;
import com.example.university.dto.request.GradeRequest;
import com.example.university.service.GradeService;
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
@RequestMapping("/api/grade")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GradeController {

    GradeService service;

    @GetMapping
    public ResponseEntity<List<GradeDTO>> getAllGrades() {
        return ResponseEntity.ok(service.getAllGrades());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GradeDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<GradeDTO> create(@RequestBody GradeRequest request) {
        return ResponseEntity.ok(service.createGrade(request));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<GradeDTO> update(@PathVariable Long id, @RequestBody GradeRequest request) {
        return ResponseEntity.ok(service.updateGrade(id, request));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteGrade(id));
    }
}
