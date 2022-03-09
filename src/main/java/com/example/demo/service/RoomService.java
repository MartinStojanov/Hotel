package com.example.demo.service;

import com.example.demo.model.Room;
import com.example.demo.model.RoomType;

import java.util.List;

public interface RoomService {
    List<Room> listAll();

    Room create(Integer number, boolean free, RoomType roomType);

    Room delete(Long id);

    Room findById(Long id);

    Room update(Long id,Integer number, boolean free, RoomType roomType);

    Long numberOfFreeRooms();
}
