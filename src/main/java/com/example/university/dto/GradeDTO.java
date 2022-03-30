package com.example.university.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GradeDTO {

    LocalDate date;
    char defaultGrade;
    Integer grade;
    String nationalScale;
    String professorFullName;
    String studentFullName;
}
