package com.example.university.service;

import com.example.university.dto.request.ProfessorRequest;
import com.example.university.mapper.ProfessorMapper;
import com.example.university.model.Professor;
import com.example.university.repository.ProfessorRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProfessorService {

    ProfessorRepository professorRepository;
    ProfessorMapper professorMapper;

    @Transactional(readOnly = true)
    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Professor getById(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity with id=" + id + " not found"));
    }

    @Transactional
    public Professor createProfessor(ProfessorRequest request) {
        Professor professor = professorMapper.toEntity(request);
        professor.setFullName(professor.getFirstName() + " " + professor.getLastName());
        return professorRepository.save(professor);
    }

    @Transactional
    public Professor updateProfessor(Long id, ProfessorRequest request) {
        Professor professor = getById(id);
        professorMapper.updateProfessorEntity(request, professor);
        professor.setFullName(professor.getFirstName() + " " + professor.getLastName());
        return professorRepository.save(professor);
    }

    @Transactional
    public Long deleteProfessor(Long id) {
        professorRepository.deleteById(id);
        return id;
    }
}
