package com.example.demo.controllers;

import com.example.demo.model.*;
import com.example.demo.service.GuestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GuestController {

    private final GuestService guestService;


    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping("/guests")
    public String listGuests(Model model){
        List<Guests> guestsList = this.guestService.listAll();
        model.addAttribute("guestsList",guestsList);
        return "listGuests";
    }

    @GetMapping("/guest/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        Guests guest = this.guestService.findById(id);
        model.addAttribute("types", AccommodationType.values());
        model.addAttribute("guest",guest);

        return "editGuest";
    }


    @PostMapping("/guest")
    public String addGuest(@RequestParam String name,
                              @RequestParam String surname,
                              @RequestParam String EMBG,
                              @RequestParam String email,
                              @RequestParam boolean breakfast,
                              @RequestParam AccommodationType type){
        this.guestService.create(name,surname,EMBG,email,breakfast,type);
        return "redirect:/guests";
    }

    @GetMapping("/guest/add")
    public String showAdd(Model model) {
        model.addAttribute("types", AccommodationType.values());
        return "editGuest";
    }

    @PostMapping("/guest/{id}")
    public String editGuest(@PathVariable Long id,
                            @RequestParam String name,
                            @RequestParam String surname,
                            @RequestParam String EMBG,
                            @RequestParam String email,
                            @RequestParam boolean breakfast,
                            @RequestParam AccommodationType type){
        this.guestService.update(id,name,surname,EMBG,email,breakfast,type);
        return "redirect:/guests";

    }
    @PostMapping("/guest/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.guestService.delete(id);
        return "redirect:/guests";
    }
}
