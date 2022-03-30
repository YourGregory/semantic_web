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

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
public class Groups extends AbstractIdentifiable {

    @Column(nullable = false)
    String name;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    Department department;

    @OneToOne
    @JoinColumn(name = "curator_id", nullable = false)
    Professor professor;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    Course course;
}
