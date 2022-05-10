package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
@Data
@Entity
public class Guests {

    public Guests() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String EMBG;
    private String email;
    private boolean breakfast;
    private float price;
    private boolean paid;


    public Guests(String name, String surname, String EMBG,
                  String email, boolean breakfast,
                  boolean paid,
                  float price) {
        this.name = name;
        this.surname = surname;
        this.EMBG = EMBG;
        this.email = email;
        this.breakfast = breakfast;
        this.paid = paid;
        this.price=price;
    }

    public Guests(String name, String surname, String EMBG,
                  String email, boolean breakfast) {
        this.name = name;
        this.surname = surname;
        this.EMBG = EMBG;
        this.email = email;
        this.breakfast = breakfast;
        this.paid=false;

    }
    public void HtmlEmailSender(){

    }

}
