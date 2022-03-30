package com.example.university.service;

import com.example.university.dto.StudentDTO;
import com.example.university.dto.request.PagingRequest;
import com.example.university.dto.request.StudentRequest;
import com.example.university.mapper.StudentMapper;
import com.example.university.model.Student;
import com.example.university.repository.GradeRepository;
import com.example.university.repository.GroupsRepository;
import com.example.university.repository.StudentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class StudentService {

    StudentRepository studentRepository;
    GroupsRepository groupsRepository;
    StudentMapper studentMapper;

    @Transactional(readOnly = true)
    public List<StudentDTO> getAllStudents() { return studentMapper.toStudentDTOs(studentRepository.findAll()); }

    @Transactional(readOnly = true)
    public StudentDTO getById(Long id) {
        Student student = findStudentById(id);
        return studentMapper.toStudentDTO(student);
    }

    @Transactional
    public StudentDTO createStudent(StudentRequest request) {
        Student student = studentMapper.toEntity(request);
        student.setFullName(student.getFirstName() + " " + student.getLastName());
        student.setGroup(groupsRepository.getById(request.getGroupId()));
        return studentMapper.toStudentDTO(studentRepository.save(student));
    }

    @Transactional
    public StudentDTO updateStudent(Long id, StudentRequest request) {
        Student student = findStudentById(id);
        studentMapper.updateStudentEntity(request, student);
        student.setFullName(student.getFirstName() + " " + student.getLastName());
        student.setGroup(groupsRepository.getById(request.getGroupId()));
        return studentMapper.toStudentDTO(studentRepository.save(student));
    }

    @Transactional
    public Long deleteStudent(Long id) {
        studentRepository.deleteById(id);
        return id;
    }

    private Student findStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity with id=" + id + " not found"));
    }

    public Page<Student> getStudentPagination(Integer size, Integer page) {

        return studentRepository.findAll(PageRequest.of(page, size));
    }
}
