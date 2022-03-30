package com.example.university.repository;

import com.example.university.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
