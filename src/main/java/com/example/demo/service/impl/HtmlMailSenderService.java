package com.example.demo.service.impl;

import com.example.demo.model.Guests;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class HtmlMailSenderService {
    private final ReservationServiceImpl reservationService;

    public HtmlMailSenderService(ReservationServiceImpl reservationService) {
        this.reservationService = reservationService;
    }

    public void triggerHtmlMail(String emailTo, String subject, String content) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.host", "smtp.gmail.com");
        props.setProperty("mail.debug", "true");


//        String to = "nikolastojancevski@yahoo.com";
        final String from = "bane.taskovic@gmail.com";
        final String password = "juatixngbmidmvic";

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });
        session.setDebug(true);
        Transport transport = session.getTransport();

        MimeMessage message = new MimeMessage(session);
        message.setSubject(subject);
        message.setFrom(new InternetAddress("bane.taskovic@gmail.com"));
        message.setContent(content, "text/html");
        message.addRecipient(Message.RecipientType.TO,
                new InternetAddress(emailTo));

        transport.connect();
        transport.sendMessage(message,
                message.getRecipients(Message.RecipientType.TO));
        transport.close();
    }
    public String generateEmailContent(Guests guest){
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>Hello ");
        sb.append(guest.getName());
        sb.append("</h1>");
        return sb.toString();
    }
}
