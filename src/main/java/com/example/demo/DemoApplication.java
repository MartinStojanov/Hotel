package com.example.demo;

import com.example.demo.model.Employee;
import com.example.demo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

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
