package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
@Data
@Entity
public class Guests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String EMBG;
    private String email;
    private LocalDate from;
    private LocalDate to;
    private boolean breakfast;
    @Enumerated(EnumType.STRING)
    private AccommodationType type;
    private float price;

    public Guests() {
    }

    public Guests(String name, String surname, String EMBG,
                  String email, LocalDate from,
                  LocalDate to, boolean breakfast,
                  AccommodationType type) {
        this.name = name;
        this.surname = surname;
        this.EMBG = EMBG;
        this.email = email;
        this.from = from;
        this.to = to;
        this.breakfast = breakfast;
        this.type = type;
    }
}
