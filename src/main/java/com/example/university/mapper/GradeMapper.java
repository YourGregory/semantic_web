package com.example.university.mapper;

import com.example.university.dto.GradeDTO;
import com.example.university.dto.request.GradeRequest;
import com.example.university.model.Grade;
import com.example.university.repository.ProfessorRepository;
import com.example.university.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Mapper(componentModel = "string")
public class GradeMapper {

    ProfessorRepository professorRepository;
    StudentRepository studentRepository;

    public GradeDTO toGradeDTO(Grade grade) {
        GradeDTO gradeDTO = new GradeDTO();
        gradeDTO.setGrade(grade.getGrade());
        gradeDTO.setDefaultGrade(grade.getDefaultGrade());
        gradeDTO.setNationalScale(grade.getNationalScale());
        gradeDTO.setDate(grade.getDate());
        gradeDTO.setProfessorFullName(grade.getProfessor().getFullName());
        gradeDTO.setStudentFullName(grade.getStudent().getFullName());

        return gradeDTO;
    }

    public List<GradeDTO> toGradeDTOs(List<Grade> grades) {
        List<GradeDTO> gradeDTOs = new ArrayList<>();
        grades.forEach(grade -> {
            GradeDTO gradeDTO = toGradeDTO(grade);
            gradeDTOs.add(gradeDTO);
        });
        return gradeDTOs;
    }

    public Grade toEntity(GradeRequest request) {
        Grade grade = new Grade();
        updateGradeEntity(request, grade);

        return grade;
    }

    public void updateGradeEntity(GradeRequest request, @MappingTarget Grade grade) {
        grade.setGrade(request.getGrade());
        grade.setProfessor(professorRepository.getById(request.getProfessorId()));
        grade.setStudent(studentRepository.getById(request.getStudentId()));

        setGradeScale(grade);
    }

    private void setGradeScale(Grade grade) {
        Integer intGrade = grade.getGrade();
        MARK mark = null;
        String nationalScale;

        if (intGrade <= 100 && intGrade >= 90) {
            mark = MARK.A;
        } else if (intGrade < 90 && intGrade >= 80) {
            mark = MARK.B;
        } else if (intGrade < 80 && intGrade >= 70) {
            mark = MARK.C;
        } else if (intGrade < 70 && intGrade >= 60) {
            mark = MARK.D;
        } else if (intGrade < 60 && intGrade >= 50) {
            mark = MARK.E;
        } else {
            mark = MARK.F;
        }
        grade.setDefaultGrade(mark.getName());
        grade.setNationalScale(mark.getNationalScale());
    }


    @AllArgsConstructor
    @Getter
    private enum MARK {
        A('A', "Perfect"),
        B('B', "Good"),
        C('C', "Good"),
        D('D', "Satisfactorily"),
        E('E', "Satisfactorily"),
        F('F', "Unsatisfactorily");

        private final char name;
        private final String nationalScale;
    }

}
