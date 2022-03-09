package com.example.demo.model;


import lombok.Data;

import javax.persistence.*;
@Data
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;
    private Boolean free;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    public Room() {
    }

    public Room(Integer number, Boolean free, RoomType roomType) {
        this.number = number;
        this.free = free;
        this.roomType = roomType;
    }
}
