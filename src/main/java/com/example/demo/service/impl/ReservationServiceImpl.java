package com.example.demo.service.impl;

import com.example.demo.model.Guests;
import com.example.demo.model.Reservations;
import com.example.demo.model.Room;
import com.example.demo.repository.ReservationRepo;
import com.example.demo.service.ReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepo reservationRepo;

    public ReservationServiceImpl(ReservationRepo reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    @Override
    public List<Reservations> listAll() {
        return this.reservationRepo.findAll();
    }

    @Override
    public Reservations create(Guests guests, Room room, String from, String to) {
        Reservations reservations = new Reservations(guests,room,from,to);
        return this.reservationRepo.save(reservations);
    }

    @Override
    public Reservations delete(Long id) {
        Reservations reservations = this.reservationRepo.findById(id).orElseThrow(RuntimeException::new);
        this.reservationRepo.delete(reservations);
        return reservations;
    }

    @Override
    public Reservations findById(Long id) {
        return this.reservationRepo.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Reservations update(Long id, Guests guests, Room room, String from, String to) {
        Reservations reservations = findById(id);
        reservations.setGuests(guests);
        reservations.setRoom(room);
        reservations.setToo(to);
        reservations.setFromm(from);
        return this.reservationRepo.save(reservations);
    }
}
