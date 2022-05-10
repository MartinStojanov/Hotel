package com.example.demo.controllers;

import com.example.demo.model.*;
import com.example.demo.service.GuestService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ReservationsController {
    private final ReservationService reservationService;
    private final GuestService guestService;
    private final RoomService roomService;

    public ReservationsController(ReservationService reservationService,
                                  GuestService guestService,
                                  RoomService roomService) {
        this.reservationService = reservationService;
        this.guestService=guestService;
        this.roomService=roomService;
    }

    @GetMapping("/reservations")
    public String listReservations(Model model){

        List<Reservations> reservationsList = this.reservationService.listAll();
        model.addAttribute("reservationsList",reservationsList);
        return "listReservations";
    }



    @PostMapping("/reservation")
    public String addReservation( @RequestParam Long guests,
                                  @RequestParam Long room,
                                  @RequestParam String fromm,
                                  @RequestParam String too){
        this.reservationService.create(this.guestService.findById(guests),
                this.roomService.findById(room),fromm,too);
        return "redirect:/reservations";
    }

    @GetMapping("/reservation/add")
    public String showAdd(Model model) {
        model.addAttribute("types", AccommodationType.values());

            List<Guests> guestsList = this.guestService.listAll();
            model.addAttribute("guestsList",guestsList);
        List<Room> roomList = this.roomService.listAll().stream().filter(x->x.getFree()).collect(Collectors.toList());

        model.addAttribute("roomList",roomList);
        return "addReservation";
    }
    @GetMapping("/reservation/add/{id}")
    public String showAddd(Model model, @PathVariable Long id) {
        model.addAttribute("types", AccommodationType.values());
        List<Guests> guestsList = new ArrayList<>();
            Guests guest = this.guestService.findById(id);
            guestsList.add(guest);
            model.addAttribute("guestsList",guestsList);
        List<Room> roomList = this.roomService.listAll().stream().filter(x->x.getFree()).collect(Collectors.toList());

        model.addAttribute("roomList",roomList);
        return "addReservation";
    }

    @GetMapping("/reservation/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        Reservations res = this.reservationService.findById(id);

        model.addAttribute("res",res);
        model.addAttribute("types",AccommodationType.values());

        return "addReservation";
    }

    @PostMapping("/reservation/{id}")
    public String update(@PathVariable Long id,
                            @RequestParam Long guests,
                            @RequestParam Long room,
                            @RequestParam String fromm,
                            @RequestParam String too){
        this.reservationService.update(id,this.guestService.findById(guests),
                this.roomService.findById(room), fromm, too);
        return "redirect:/reservations";

    }
    @PostMapping("/reservation/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.reservationService.delete(id);
        return "redirect:/reservations";
    }
}
