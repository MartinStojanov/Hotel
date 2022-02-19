package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
@Data
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

    public Reservations() {
    }

    public Reservations(Guests guests, Room room, LocalDate from, LocalDate to) {
        this.guests = guests;
        this.room = room;
        this.from = from;
        this.to = to;
    }
}
