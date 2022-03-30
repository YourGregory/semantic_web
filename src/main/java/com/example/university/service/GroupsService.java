package com.example.university.service;

import com.example.university.dto.GroupDTO;
import com.example.university.dto.request.GroupRequest;
import com.example.university.mapper.GroupMapper;
import com.example.university.model.Groups;
import com.example.university.repository.GroupsRepository;
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
public class GroupsService {

    GroupsRepository groupsRepository;
    GroupMapper groupMapper;

    @Transactional(readOnly = true)
    public List<GroupDTO> getAllGroups() {
        return groupMapper.toGroupDTOs(groupsRepository.findAll());
    }

    @Transactional(readOnly = true)
    public GroupDTO getById(Long id) {
        Groups groups = findGroupById(id);
        return groupMapper.toGroupDTO(groups);
    }

    @Transactional
    public GroupDTO createGroup(GroupRequest request) {
        return groupMapper.toGroupDTO(groupsRepository.save(groupMapper.toEntity(request)));
    }

    @Transactional
    public GroupDTO updateGroup(Long id, GroupRequest request) {
        Groups group = findGroupById(id);
        groupMapper.updateGroupEntity(request, group);

        return groupMapper.toGroupDTO(groupsRepository.save(group));
    }

    @Transactional
    public Long deleteGroup(Long id) {
        groupsRepository.deleteById(id);

        return id;
    }

    private Groups findGroupById(Long id) {
        return groupsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity with id=" + id + " not found"));
    }
}
