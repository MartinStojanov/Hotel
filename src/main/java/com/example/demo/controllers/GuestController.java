package com.example.demo.controllers;

import com.example.demo.model.*;
import com.example.demo.repository.GuestRepo;
import com.example.demo.service.GuestService;
import com.example.demo.service.impl.HtmlMailSenderService;
import com.lowagie.text.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class GuestController {

    private final GuestService guestService;
    private final HtmlMailSenderService htmlMailSenderService;
    private final GuestRepo guestRepo;

    public GuestController(GuestService guestService, HtmlMailSenderService htmlMailSenderService, GuestRepo guestRepo) {
        this.guestService = guestService;
        this.htmlMailSenderService = htmlMailSenderService;
        this.guestRepo = guestRepo;
    }

    @GetMapping("/guests")
    public String listGuests(Model model) throws MessagingException {
        List<Guests> guestsList = this.guestService.listAll();
        model.addAttribute("guestsList",guestsList);
        return "listGuests";
    }

    @GetMapping("/guest/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        Guests guest = this.guestService.findById(id);
        model.addAttribute("guest",guest);
        return "addGuest";
    }


    @PostMapping("/guest")
    public String addGuest(@RequestParam String name,
                              @RequestParam String surname,
                              @RequestParam String EMBG,
                              @RequestParam String email,
                              @RequestParam boolean breakfast){
        this.guestService.create(name,surname,EMBG,email,breakfast);
        return "redirect:/guests";
    }

    @GetMapping("/guest/add")
    public String showAdd(Model model) {
        return "addGuest";
    }

    @GetMapping("/guest/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=guests_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Guests> listGuests = guestService.listAll();

        GuestPDFExporter exporter = new GuestPDFExporter(listGuests);
        exporter.export(response);

    }


    @PostMapping("/guest/{id}")
    public String editGuest(@PathVariable Long id,
                            @RequestParam String name,
                            @RequestParam String surname,
                            @RequestParam String EMBG,
                            @RequestParam String email,
                            @RequestParam boolean breakfast
                            ){
        this.guestService.update(id,name,surname,EMBG,email,breakfast);
        return "redirect:/guests";

    }
    @GetMapping("/guest/{id}/send-email")
    public String sendEmail(@PathVariable Long id) throws MessagingException {
        Guests guest =  this.guestService.findById(id);
        String subject = "HOTEL INVOICE";
        String content = this.htmlMailSenderService.generateEmailContent(guest);
        this.htmlMailSenderService.triggerHtmlMail(guest.getEmail(),subject,content);
        return "redirect:/guests";
    }
    @PostMapping("/guest/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.guestService.delete(id);
        return "redirect:/guests";
    }

    @GetMapping("/guests/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=guests_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Guests> guestsList = this.guestService.listAll();

        GuestExcelExporter excelExporter = new GuestExcelExporter(guestsList);

        excelExporter.export(response);
    }

    @GetMapping("/uploadPageGuest")
    public String getUploadGuest(){
        return "uploadCSVGuest";
    }

    @PostMapping(value = "/uploadGuest", consumes = "text/csv")
    public String uploadSimpleGuest(@RequestBody InputStream body) throws IOException {
        guestRepo.saveAll(CsvUtils.read(Guests.class, body));

        return "redirect:/guests";
    }

    @PostMapping(value = "/uploadGuest", consumes = "multipart/form-data")
    public String uploadMultipartGuest(@RequestParam("file") MultipartFile file) throws IOException {
        guestRepo.saveAll(CsvUtils.read(Guests.class, file.getInputStream()));
        return "redirect:/guests";
    }

}
