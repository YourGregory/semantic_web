package com.example.university.dto;

import com.example.university.model.Groups;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentDTO {

    LocalDate birthday;
    String firstName;
    String lastName;
    Groups groups;
}
