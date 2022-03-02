package com.example.demo.service.impl;

import com.example.demo.model.AccommodationType;
import com.example.demo.model.Guests;
import com.example.demo.repository.GuestRepo;
import com.example.demo.service.GuestService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class GuestServiceImpl implements GuestService {
    private final GuestRepo guestRepo;

    public GuestServiceImpl(GuestRepo guestRepo) {
        this.guestRepo = guestRepo;
    }

    @Override
    public List<Guests> listAll() {
        return this.guestRepo.findAll();
    }

    @Override
    public Guests create(String name, String surname, String EMBG, String email, boolean breakfast, AccommodationType type) {
        Guests guests = new Guests( name,  surname,  EMBG,  email,   breakfast,  type);
        return guestRepo.save(guests);
    }

    @Override
    public Guests findById(Long id) {
        Guests guests = this.guestRepo.findById(id).orElseThrow(RuntimeException::new);
        return guests;
    }

    @Override
    public Guests delete(Long id) {
        Guests guests = this.findById(id);
        this.guestRepo.delete(guests);
        return guests;
    }


    @Override
    public Guests update(Long id, String name, String surname, String EMBG, String email,  boolean breakfast, AccommodationType type) {
        Guests guests = findById(id);
        guests.setName(name);
        guests.setSurname(surname);
        guests.setEmail(email);
        guests.setEMBG(EMBG);

        guests.setBreakfast(breakfast);
        guests.setType(type);
        return this.guestRepo.save(guests);
    }
}
