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
import javax.persistence.Table;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
public class Subject extends AbstractIdentifiable {

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    Integer hours;

    @Column(nullable = false)
    Integer credits;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    Professor professor;
}
