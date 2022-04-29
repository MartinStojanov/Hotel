package com.example.demo.controllers;

import com.example.demo.model.AccommodationType;
import com.example.demo.model.Guests;
import com.example.demo.model.Reservations;
import com.example.demo.model.Room;
import com.example.demo.service.GuestService;
import com.example.demo.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ReservationsController {
    private final ReservationService reservationService;
    private final GuestService guestService;

    public ReservationsController(ReservationService reservationService, GuestService guestService) {
        this.reservationService = reservationService;
        this.guestService = guestService;
    }

    @GetMapping("/reservations")
    public String listReservations(Model model){

        List<Reservations> reservationsList = this.reservationService.listAll();
        model.addAttribute("reservationsList",reservationsList);
        return "listReservations";
    }



    @PostMapping("/addReservations")
    public String addReservation(@RequestParam Guests guests,
                              @RequestParam Room room,
                              @RequestParam LocalDate fromm,
                              @RequestParam LocalDate too){
        this.reservationService.create(guests,room,fromm,too);
        return "redirect:/reservations";
    }

    @GetMapping("/reservation/{id}/add")
    public String showAdd(Model model, @PathVariable Long id) {
        model.addAttribute("types", AccommodationType.values());
        model.addAttribute("id", id);
        return "addReservation";
    }

    @PostMapping("/reservation/{id}")
    public String editReservation(@PathVariable Long id,
                            @RequestParam Guests guests,
                            @RequestParam Room room,
                            @RequestParam LocalDate fromm,
                            @RequestParam LocalDate too){
        this.reservationService.update(id,guests,room,fromm,too);
        return "redirect:/reservations";

    }
    @PostMapping("/reservation/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.reservationService.delete(id);
        return "redirect:/reservation";
    }
}
