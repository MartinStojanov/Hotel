package com.example.demo.model;

import javax.persistence.*;

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

    public Employee() {
    }

    public Employee(String name, String surname, String EMBG, String email, float salary, Role position) {

        this.name = name;
        this.surname = surname;
        this.EMBG = EMBG;
        this.email = email;
        this.salary = salary;
        this.position = position;
    }
}
