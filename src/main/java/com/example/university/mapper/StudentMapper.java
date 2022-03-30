package com.example.university.mapper;

import com.example.university.dto.StudentDTO;
import com.example.university.dto.request.StudentRequest;
import com.example.university.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDTO toStudentDTO(Student student);

    List<StudentDTO> toStudentDTOs(List<Student> students);

    Student toEntity(StudentRequest request);

    void updateStudentEntity(StudentRequest request, @MappingTarget Student student);
}
