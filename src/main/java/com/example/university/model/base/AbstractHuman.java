package com.example.university.model.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class AbstractHuman extends AbstractIdentifiable{
    @Column(nullable = false)
    String lastName;

    @Column(nullable = false)
    String firstName;

    @Column(nullable = false)
    LocalDate birthday;

    @Column
    String fullName;
}
