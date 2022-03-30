package com.example.university.model;

import com.example.university.model.base.AbstractIdentifiable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
public class Grade extends AbstractIdentifiable {

    @Column
    Integer grade;

    @Column
    char defaultGrade;

    @Column
    String nationalScale;

    @Column(nullable = false)
    LocalDate date;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    Professor professor;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    Student student;
}
