package com.example.university.service;

import com.example.university.dto.SubjectDTO;
import com.example.university.dto.request.SubjectRequest;
import com.example.university.mapper.ProfessorMapper;
import com.example.university.mapper.SubjectMapper;
import com.example.university.model.Subject;
import com.example.university.repository.ProfessorRepository;
import com.example.university.repository.SubjectRepository;
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
public class SubjectService {

    SubjectRepository subjectRepository;
    ProfessorRepository professorRepository;

    SubjectMapper subjectMapper;

    @Transactional(readOnly = true)
    public List<SubjectDTO> getAllSubjects() {
        return subjectMapper.toSubjectDTOs(subjectRepository.findAll());
    }

    @Transactional(readOnly = true)
    public SubjectDTO getById(Long id) {
        Subject subject = findSubjectById(id);
        return subjectMapper.toSubjectDTO(subject);
    }

    @Transactional
    public SubjectDTO createSubject(SubjectRequest request) {
        return subjectMapper.toSubjectDTO(subjectRepository.save(subjectMapper.toEntity(request)));
    }

    @Transactional
    public SubjectDTO updateSubject(Long id, SubjectRequest request) {
        Subject subject = findSubjectById(id);
        subjectMapper.updateSubjectEntity(request, subject);
        subject.setProfessor(professorRepository.getById(request.getProfessorId()));
        return subjectMapper.toSubjectDTO(subjectRepository.save(subject));
    }

    @Transactional
    public Long deleteSubject(Long id) {
        subjectRepository.deleteById(id);
        return id;
    }

    private Subject findSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject with id=" + id + " not found"));
    }
}
