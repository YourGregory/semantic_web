package com.example.university.controller;

import com.example.university.dto.GroupDTO;
import com.example.university.dto.request.GroupRequest;
import com.example.university.service.GroupsService;
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
@RequestMapping("/api/group")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GroupsController {

    GroupsService service;

    @GetMapping
    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        return ResponseEntity.ok(service.getAllGroups());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GroupDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<GroupDTO> createGroup(@RequestBody GroupRequest request) {
        return ResponseEntity.ok(service.createGroup(request));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<GroupDTO> updateGroup(@PathVariable Long id, @RequestBody GroupRequest request) {
        return ResponseEntity.ok(service.updateGroup(id, request));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Long> deleteGroup(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteGroup(id));
    }
}
