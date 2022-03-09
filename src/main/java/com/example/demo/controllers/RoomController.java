package com.example.demo.controllers;

import com.example.demo.model.Employee;
import com.example.demo.model.Room;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RoomController {
    private final RoomService roomService;
    private final EmployeeService employeeService;
    public RoomController(RoomService roomService, EmployeeService employeeService) {
        this.roomService = roomService;
        this.employeeService = employeeService;
    }


    @GetMapping("/listRooms")
    public String listRooms(Model model){

        List<Room> roomList = this.roomService.listAll();
        model.addAttribute("roomList",roomList);
        return "listRooms";
    }

    @GetMapping("/roomTest")
    public String showTest(Model model) {
        List<Employee> employeeList = this.employeeService.listAll();
        model.addAttribute("employees",employeeList);
        model.addAttribute("numberFreeRooms",roomService.numberOfFreeRooms());
        model.addAttribute("numberBusyRooms", (long) roomService.listAll().size() - roomService.numberOfFreeRooms());
        return "index";
    }
}
