package com.example.demo.model;


import javax.persistence.*;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;
    private boolean free;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

}
