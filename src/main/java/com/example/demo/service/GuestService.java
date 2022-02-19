package com.example.demo.service;

import com.example.demo.model.AccommodationType;
import com.example.demo.model.Employee;
import com.example.demo.model.Guests;
import com.example.demo.model.Role;

import java.time.LocalDate;
import java.util.List;

public interface GuestService {
    List<Guests> listAll();

    Guests create(String name, String surname, String EMBG,
                  String email, LocalDate from,
                  LocalDate to, boolean breakfast,
                  AccommodationType type);

    Guests delete(Long id);

    Guests findById(Long id);

    Guests update(Long id,String name, String surname, String EMBG,
                  String email, boolean breakfast,
                  AccommodationType type);
}
