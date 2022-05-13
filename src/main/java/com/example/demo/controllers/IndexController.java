package com.example.demo.controllers;

import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.mail.MessagingException;
import java.util.List;

@Controller
public class IndexController {

    private final GuestService guestService;
    private final EmployeeService employeeService;
    private final ProductService productService;
    private final ReservationService reservationService;
    private final RoomService roomService;

    public IndexController(GuestService guestService, EmployeeService employeeService, ProductService productService, ReservationService reservationService, RoomService roomService) {
        this.guestService = guestService;
        this.employeeService = employeeService;
        this.productService = productService;
        this.reservationService = reservationService;
        this.roomService = roomService;
    }

    @GetMapping({"/index","/"})
    public String getInfo(Model model) {

        List<Guests> guestsList = this.guestService.listAll();
        List<Product> productList = this.productService.listAll();
        List<Employee> employeeList = this.employeeService.listAll();
        List<Room> roomList = this.roomService.listAll();
        List<Reservations> reservationsList = this.reservationService.listAll();


        model.addAttribute("guestsList",guestsList);
        model.addAttribute("productList",productList);
        model.addAttribute("employeeList",employeeList);
        model.addAttribute("roomList",roomList);
        model.addAttribute("reservationsList",reservationsList);
        model.addAttribute("numberFree", roomService.numberOfFreeRooms());

        return "index";
    }

}
