package com.example.demo.repository;

import com.example.demo.model.Guests;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepo extends JpaRepository<Guests,Long> {
}
