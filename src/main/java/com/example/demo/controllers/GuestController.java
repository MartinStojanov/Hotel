package com.example.demo.controllers;

import com.example.demo.model.AccommodationType;
import com.example.demo.model.Guests;
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

    @GetMapping("/listGuests")
    public String listEmployees(Model model){

        List<Guests> guestsList = this.guestService.listAll();
//        int broj = guestsList.size();
//        model.addAttribute("brojNaGosti",broj);
        model.addAttribute("guestsList",guestsList);
        return "listEmployees";
    }

    //eden Get Mapping so input forma za addGuest
    //TODO:
    //@GetMapping


    @PostMapping("/addGuest")
    public String addEmployee(@RequestParam String name,
                              @RequestParam String surname,
                              @RequestParam String EMBG,
                              @RequestParam String email,
                              @RequestParam boolean breakfast,
                              @RequestParam AccommodationType type){
        this.guestService.create(name,surname,EMBG,email,breakfast,type);
        return "redirect:/listEmployees";//da se smeni Strana so nov gost
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
        return "redirect:/guests";//da se smeni Strana so updated rabotnik

    }
    @PostMapping("/guest/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.guestService.delete(id);
        return "redirect:/guests"; // da se smeni strana so izbrisan rabotnik
    }
}
