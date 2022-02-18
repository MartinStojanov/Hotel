package com.example.demo.model;

import javax.persistence.*;
import java.util.List;
@Entity
public class Magacine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Product> products;


}
