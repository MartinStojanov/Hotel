package com.example.demo.service;

import com.example.demo.model.Guests;
import com.example.demo.model.Reservations;
import com.example.demo.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {
    List<Room> listAll();

    Room create(Guests guests, Room room, LocalDate from, LocalDate to);

    Room delete(Long id);

    Room findById(Long id);

    Room update(Long id,Guests guests, Room room, LocalDate from, LocalDate to);
}
