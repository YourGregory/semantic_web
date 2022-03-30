package com.example.university.mapper;

import com.example.university.dto.request.SubjectRequest;
import com.example.university.dto.SubjectDTO;
import com.example.university.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    SubjectDTO toSubjectDTO(Subject subject);

    List<SubjectDTO> toSubjectDTOs(List<Subject> subjects);

    Subject toEntity(SubjectRequest request);

    void updateSubjectEntity(SubjectRequest request, @MappingTarget Subject subject);
}
