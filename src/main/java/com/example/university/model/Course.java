package com.example.university.model;

import com.example.university.model.base.AbstractIdentifiable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table
public class Course extends AbstractIdentifiable {

    @Column(nullable = false)
    Integer year;

    @Column(nullable = false)
    Boolean master;
}
