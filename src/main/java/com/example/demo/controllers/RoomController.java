package com.example.demo.controllers;

import com.example.demo.model.Employee;
import com.example.demo.model.Guests;
import com.example.demo.model.Room;
import com.example.demo.model.RoomType;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RoomController {
    private final RoomService roomService;
    public RoomController(RoomService roomService, EmployeeService employeeService) {
        this.roomService = roomService;
    }


    @GetMapping("/rooms")
    public String listRooms(Model model){

        List<Room> roomList = this.roomService.listAll();
        model.addAttribute("roomList",roomList);
        return "listRooms";
    }

    @GetMapping("/room/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        Room room = this.roomService.findById(id);
        model.addAttribute("room", room);
        model.addAttribute("types",RoomType.values());
        return "addRoom";
    }


    @PostMapping("/room")
    public String addRoom(@RequestParam Integer number,
                           @RequestParam RoomType type,
                           @RequestParam boolean free){
        this.roomService.create(number,free,type);
        return "redirect:/rooms";
    }

    @GetMapping("/room/add")
    public String showAdd(Model model) {
        model.addAttribute("types",RoomType.values());
        return "addRoom";
    }

    @PostMapping("/room/{id}")
    public String editGuest(
                            @PathVariable Long id,
                            @RequestParam Integer number,
                            @RequestParam RoomType type,
                            @RequestParam boolean free){
        this.roomService.update(id,number,free,type);
        return "redirect:/rooms";

    }

    @PostMapping("/room/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.roomService.delete(id);
        return "redirect:/rooms";
    }
}
