package com.example.demo.service.impl;

import com.example.demo.model.Guests;
import com.example.demo.model.Room;
import com.example.demo.model.RoomType;
import com.example.demo.repository.RoomRepo;
import com.example.demo.service.RoomService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepo roomRepo;

    public RoomServiceImpl(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }


    @Override
    public List<Room> listAll() {
        return this.roomRepo.findAll();
    }

    @Override
    public Room create(Integer number, boolean free, RoomType roomType) {
        Room room = new Room(number,free,roomType);
        return this.roomRepo.save(room);
    }

    @Override
    public Room delete(Long id) {
        Room room = this.roomRepo.findById(id).orElseThrow(RuntimeException::new);
        this.roomRepo.delete(room);
        return room;
    }

    @Override
    public Room findById(Long id) {
        return this.roomRepo.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Room update(Long id, Integer number, boolean free, RoomType roomType) {
        Room room = findById(id);
        room.setNumber(number);
        room.setFree(free);
        room.setRoomType(roomType);
        return this.roomRepo.save(room);
    }
}
