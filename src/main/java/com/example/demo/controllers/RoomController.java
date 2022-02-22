package com.example.demo.controllers;

import com.example.demo.model.Room;
import com.example.demo.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    @GetMapping("/listRooms")
    public String listRooms(Model model){

        List<Room> roomList = this.roomService.listAll();
        model.addAttribute("roomList",roomList);
        return "listRooms";
    }
}
