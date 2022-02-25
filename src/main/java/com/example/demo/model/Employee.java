package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String EMBG;
    private String email;
    private float salary;
    @Enumerated(EnumType.STRING)
    private Role position;
    // so komentar !!!
    public Employee() {
    }

    public Employee(String name, String surname, String EMBG, String email, float salary, Role position) {
//test2
        this.name = name;
        this.surname = surname;
        this.EMBG = EMBG;
        this.email = email;
        this.salary = salary;
        this.position = position;
    }
}
