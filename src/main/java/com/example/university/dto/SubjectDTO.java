package com.example.university.dto;

import com.example.university.model.Professor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubjectDTO {

    Integer credits;
    Integer hours;
    String name;
    Professor professor;
}
