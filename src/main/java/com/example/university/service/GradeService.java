package com.example.university.service;

import com.example.university.dto.GradeDTO;
import com.example.university.dto.request.GradeRequest;
import com.example.university.mapper.GradeMapper;
import com.example.university.model.Grade;
import com.example.university.repository.GradeRepository;
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
public class GradeService {

    GradeRepository gradeRepository;
    GradeMapper gradeMapper;

    @Transactional(readOnly = true)
    public List<GradeDTO> getAllGrades() { return gradeMapper.toGradeDTOs(gradeRepository.findAll()); }

    @Transactional(readOnly = true)
    public GradeDTO getById(Long id) {
        Grade grade = findGradeById(id);
        return gradeMapper.toGradeDTO(grade);
    }

    @Transactional
    public GradeDTO createGrade(GradeRequest request) { return gradeMapper.toGradeDTO(gradeRepository.save(gradeMapper.toEntity(request))); }

    @Transactional
    public GradeDTO updateGrade(Long id, GradeRequest request) {
        Grade grade = findGradeById(id);
        gradeMapper.updateGradeEntity(request, grade);
        return gradeMapper.toGradeDTO(gradeRepository.save(grade));
    }

    @Transactional
    public Long deleteGrade(Long id) {
        gradeRepository.deleteById(id);
        return id;
    }

    private Grade findGradeById(Long id) {
        return gradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity with id=" + id + " not found"));
    }
}
