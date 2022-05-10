package com.example.demo.service;

import com.example.demo.model.Guests;
import com.example.demo.model.Reservations;
import com.example.demo.model.Room;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReservationService {
    List<Reservations> listAll();

    Reservations create(Guests guests, Room room, String from, String to);

    Reservations delete(Long id);

    Reservations findById(Long id);

    Reservations update(Long id,Guests guests, Room room, String from, String to);
}
