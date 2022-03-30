package com.example.university.mapper;

import com.example.university.dto.request.CourseRequest;
import com.example.university.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CourseMapper {

    Course toEntity(CourseRequest request);

    void updateCourseEntity(CourseRequest request, @MappingTarget Course course);
}
