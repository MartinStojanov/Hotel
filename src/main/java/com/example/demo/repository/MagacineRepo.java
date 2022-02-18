package com.example.demo.repository;

import com.example.demo.model.Magacine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MagacineRepo extends JpaRepository<Magacine,Long> {
}
