package com.example.university.mapper;

import com.example.university.dto.GroupDTO;
import com.example.university.dto.request.GroupRequest;
import com.example.university.model.Groups;
import com.example.university.repository.CourseRepository;
import com.example.university.repository.DepartmentRepository;
import com.example.university.repository.ProfessorRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GroupMapper {

    ProfessorRepository professorRepository;
    CourseRepository courseRepository;
    DepartmentRepository departmentRepository;

    public GroupDTO toGroupDTO(Groups group) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setName(group.getName());
        groupDTO.setCourse(group.getCourse().getYear());
        groupDTO.setDepartmentName(group.getDepartment().getName());
        groupDTO.setCuratorFullName(group.getProfessor().getFullName());

        return groupDTO;
    }

    public List<GroupDTO> toGroupDTOs(List<Groups> groups) {
        List<GroupDTO> groupDTOs = new ArrayList<>();
        groups.forEach(group -> {
            GroupDTO groupDTO = toGroupDTO(group);
            groupDTOs.add(groupDTO);
        });

        return groupDTOs;
    }

    public Groups toEntity(GroupRequest request) {
        Groups group = new Groups();
        updateGroupEntity(request, group);
        return group;
    }

    public void updateGroupEntity(GroupRequest request, Groups group) {
        group.setName(request.getName());
        group.setCourse(courseRepository.getById(request.getCourseId()));
        group.setDepartment(departmentRepository.getById(request.getDepartmentId()));
        group.setProfessor(professorRepository.getById(request.getCuratorId()));
    }
}
