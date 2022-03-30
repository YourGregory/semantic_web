package com.example.university.controller;

import com.example.university.dto.StudentDTO;
import com.example.university.dto.request.PagingRequest;
import com.example.university.dto.request.StudentRequest;
import com.example.university.model.Student;
import com.example.university.service.StudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/student")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class StudentController {

    StudentService service;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(service.getAllStudents());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentRequest request) {
        return ResponseEntity.ok(service.createStudent(request));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentRequest request) {
        return ResponseEntity.ok(service.updateStudent(id, request));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Long> deleteStudent(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteStudent(id));
    }

    @GetMapping(path = "paging/{size}&{page}")
    @ResponseBody
    public ResponseEntity<Page<Student>> getStudentsPaging(@PathVariable Integer size, @PathVariable Integer page) {
        return ResponseEntity.ok(service.getStudentPagination(size, page));
    }
}
