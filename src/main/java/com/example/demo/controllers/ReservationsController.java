package com.example.demo.controllers;

import com.example.demo.model.AccommodationType;
import com.example.demo.model.Guests;
import com.example.demo.model.Reservations;
import com.example.demo.model.Room;
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

    public ReservationsController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/listReservations")
    public String listReservations(Model model){

        List<Reservations> reservationsList = this.reservationService.listAll();
        model.addAttribute("reservationsList",reservationsList);
        return "listReservations";
    }

    //eden Get Mapping so input forma za addReservation
    //TODO:
    //@GetMapping


    @PostMapping("/addReservation")
    public String addEmployee(@RequestParam Guests guests,
                              @RequestParam Room room,
                              @RequestParam LocalDate fromm,
                              @RequestParam LocalDate too){
        this.reservationService.create(guests,room,fromm,too);
        return "redirect:/listReservations";//da se smeni Strana so nov gost
    }

    @GetMapping("/reservation/add")
    public String showAdd(Model model) {
        model.addAttribute("types", AccommodationType.values());
        return "editGuest";
    }

    @PostMapping("/reservation/{id}")
    public String editGuest(@PathVariable Long id,
                            @RequestParam Guests guests,
                            @RequestParam Room room,
                            @RequestParam LocalDate fromm,
                            @RequestParam LocalDate too){
        this.reservationService.update(id,guests,room,fromm,too);
        return "redirect:/reservations";//da se smeni Strana so updated rabotnik

    }
    @PostMapping("/reservation/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.reservationService.delete(id);
        return "redirect:/reservation"; // da se smeni strana so izbrisan rabotnik
    }
}
