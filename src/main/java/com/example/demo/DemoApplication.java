package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.mail.*;

@SpringBootApplication
public class DemoApplication {

//    @Autowired
//    private EmailSenderService emailSenderService;

    public static void main(String[] args) throws MessagingException {
        SpringApplication.run(DemoApplication.class, args);



    }
//    emailSenderService.sendSimpleEmail("nikolastojancevski@yahoo.com","<html><h1>PEDERISTE</h1>" +
//                "<h2 >OD BELIMBEGOVO</h2></html>" ,"Peder2");
//    }

}
