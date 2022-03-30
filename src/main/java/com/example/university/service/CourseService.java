package com.example.university.service;

import com.example.university.dto.request.CourseRequest;
import com.example.university.mapper.CourseMapper;
import com.example.university.model.Course;
import com.example.university.repository.CourseRepository;
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
public class CourseService {

    CourseRepository courseRepository;
    CourseMapper courseMapper;

    @Transactional(readOnly = true)
    public List<Course> getAllCourses() { return courseRepository.findAll(); }

    @Transactional(readOnly = true)
    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity with id=" + id + " not found"));
    }

    @Transactional
    public Course createCourse(CourseRequest request) {
        return courseRepository.save(courseMapper.toEntity(request));
    }

    @Transactional
    public Course updateCourse(Long id, CourseRequest request) {
        Course course = findById(id);
        courseMapper.updateCourseEntity(request, course);
        return courseRepository.save(course);
    }

    @Transactional
    public Long deleteCourse(Long id) {
        courseRepository.deleteById(id);
        return id;
    }
}
