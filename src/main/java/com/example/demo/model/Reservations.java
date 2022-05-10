package com.example.demo.model;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
public class Reservations {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @OnDelete(action = OnDeleteAction.CASCADE)
        @OneToOne
        private Guests guests;
        @OnDelete(action = OnDeleteAction.CASCADE)
        @OneToOne
        private Room room;
        private String fromm;
        private String too;

        public Reservations() {
        }

        public Reservations(Guests guests, Room room, String from, String to) {
            this.guests = guests;
            this.room = room;
            this.fromm = from;
            this.too = to;
        }

    }


