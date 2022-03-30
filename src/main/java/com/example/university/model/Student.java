package com.example.university.model;


import com.example.university.model.base.AbstractHuman;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

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
public class Student extends AbstractHuman {

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    Groups group;
}
