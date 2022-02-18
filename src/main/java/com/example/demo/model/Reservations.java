package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Guests guests;
    @OneToOne
    private Room room;
    private LocalDate from;
    private LocalDate to;
}
