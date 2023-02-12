package com.example.demo.service.impl;

import com.example.demo.model.Guests;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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



        final String from = "martin.stojanov@gmail.com";
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
        message.setFrom(new InternetAddress("martin.stojanov@gmail.com"));
        message.setContent(content, "text/html");
        message.addRecipient(Message.RecipientType.TO,
                new InternetAddress(emailTo));

        transport.connect();
        transport.sendMessage(message,
                message.getRecipients(Message.RecipientType.TO));
        transport.close();
    }
    public String generateEmailContent(Guests guest){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        StringBuilder sb = new StringBuilder();
        sb.append("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">\n" +
                "\t\t\t\t<tbody><tr>\n" +
                "\t\t\t\t\t<td align=\"center\" valign=\"top\">\n" +
                "\t\t\t\t\t\t<div id=\"m_8823811512548762541template_header_image\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" id=\"m_8823811512548762541template_container\" style=\"background-color:#ffffff;border:1px solid #dedede;border-radius:3px\">\n" +
                "\t\t\t\t\t\t\t<tbody><tr>\n" +
                "\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" id=\"m_8823811512548762541template_header\" style=\"background-color:#337ab7;color:#ffffff;border-bottom:0;font-weight:bold;line-height:100%;vertical-align:middle;font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,sans-serif;border-radius:3px 3px 0 0\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<tbody><tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td id=\"m_8823811512548762541header_wrapper\" style=\"padding:36px 48px;display:block\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<h1 style=\"font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,sans-serif;font-size:30px;font-weight:300;line-height:150%;margin:0;text-align:left;color:#ffffff;background-color:inherit\">Thanks for staying at our HOTEL</h1>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t</tbody></table>\n" +
                "\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" id=\"m_8823811512548762541template_body\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<tbody><tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td valign=\"top\" id=\"m_8823811512548762541body_content\" style=\"background-color:#ffffff\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"20\" cellspacing=\"0\" width=\"100%\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<tbody><tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td valign=\"top\" style=\"padding:48px 48px 32px\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div id=\"m_8823811512548762541body_content_inner\" style=\"color:#636363;font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,sans-serif;font-size:14px;line-height:150%;text-align:left\">\n" +
                "\n" +
                "<p style=\"margin:0 0 16px\">Hello <b>");
        sb.append(guest.getName());
        sb.append("</b>,</p>\n" +
                "<p style=\"margin:0 0 16px\">We want to inform you that your invoice is ready to be paid");
        sb.append("</p>\n" +
                "\n" +
                "\n" +
                "<h2 style=\"color:#337ab7;display:block;font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,sans-serif;font-size:18px;font-weight:bold;line-height:130%;margin:0 0 18px;text-align:left\">\n" +
                "\t[INVOICE #");
        sb.append("2200"+guest.getId()+" ,"+dtf.format(now));
        sb.append("]</h2>\n" +
                "\n" +
                "<div style=\"margin-bottom:40px\">\n" +
                "\t<table cellspacing=\"0\" cellpadding=\"6\" border=\"1\" style=\"color:#636363;border:1px solid #e5e5e5;vertical-align:middle;width:100%;font-family:'Helvetica Neue',Helvetica,Roboto,Arial,sans-serif\">\n" +
                "\t\t<thead>\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<th scope=\"col\" style=\"color:#636363;border:1px solid #e5e5e5;vertical-align:middle;padding:12px;text-align:left\">Accommodation</th>\n" +
                "\t\t\t\t<th scope=\"col\" style=\"color:#636363;border:1px solid #e5e5e5;vertical-align:middle;padding:12px;text-align:left\">Price</th>\n" +
                "\t\t\t</tr>\n" +
                "\t\t</thead>\n" +
                "\t\t<tbody>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t<td style=\"color:#636363;border:1px solid #e5e5e5;padding:12px;text-align:left;vertical-align:middle;font-family:'Helvetica Neue',Helvetica,Roboto,Arial,sans-serif;word-wrap:break-word\">\n" +
                "\t\t"+"Stay at our HOTEL"+"\t\t</td>\n" +
                "\t\t\n" +
                "\t\t<td style=\"color:#636363;border:1px solid #e5e5e5;padding:12px;text-align:left;vertical-align:middle;font-family:'Helvetica Neue',Helvetica,Roboto,Arial,sans-serif\">\n" +
                "\t\t\t<span>"+guest.getPrice()+"&euro;</span>\t\t</td>\n" +
                "\t</tr>\n" +
                "\t\n" +
                "\t\t</tbody>\n" +
                "\t\t<tfoot>\n" +
                "\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<th scope=\"row\" colspan=\"2\" style=\"color:#636363;border:1px solid #e5e5e5;vertical-align:middle;padding:12px;text-align:left;border-top-width:4px\">Without tax:</th>\n" +
                "\t\t\t\t\t\t<td style=\"color:#636363;border:1px solid #e5e5e5;vertical-align:middle;padding:12px;text-align:left;border-top-width:4px\"><span>"+guest.getPrice()+"&euro;</span></td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<th scope=\"row\" colspan=\"2\" style=\"color:#636363;border:1px solid #e5e5e5;vertical-align:middle;padding:12px;text-align:left\">Payment options:</th>\n" +
                "\t\t\t\t\t\t<td style=\"color:#636363;border:1px solid #e5e5e5;vertical-align:middle;padding:12px;text-align:left\">PayPal or Bank Transfer</td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<th scope=\"row\" colspan=\"2\" style=\"color:#636363;border:1px solid #e5e5e5;vertical-align:middle;padding:12px;text-align:left\">Total with tax:</th>\n" +
                "\t\t\t\t\t\t<td style=\"color:#636363;border:1px solid #e5e5e5;vertical-align:middle;padding:12px;text-align:left\"><span>"+guest.getPrice()*1.18+"&euro;</span></td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t</tfoot>\n" +
                "\t</table>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "<table id=\"m_8823811512548762541addresses\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width:100%;vertical-align:top;margin-bottom:40px;padding:0\">\n" +
                "\t<tbody><tr>\n" +
                "\t\t<td valign=\"top\" width=\"50%\" style=\"text-align:left;font-family:'Helvetica Neue',Helvetica,Roboto,Arial,sans-serif;border:0;padding:0\">\n" +
                "\t\t\t<h2 style=\"color:#337ab7;display:block;font-family:&quot;Helvetica Neue&quot;,Helvetica,Roboto,Arial,sans-serif;font-size:"+guest.getPrice()+"px;font-weight:bold;line-height:130%;margin:0 0 18px;text-align:left\">");
        return sb.toString();
    }
}
