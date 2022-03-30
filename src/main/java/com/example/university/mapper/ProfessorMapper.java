package com.example.university.mapper;

import com.example.university.dto.request.ProfessorRequest;
import com.example.university.model.Professor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {

    Professor toEntity(ProfessorRequest request);

    void updateProfessorEntity(ProfessorRequest request, @MappingTarget Professor professor);
}
