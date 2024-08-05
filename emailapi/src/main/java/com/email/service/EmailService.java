package com.email.service;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

    public boolean sendEmail(String subject, String message, String to) {
        boolean f  = false;

        // Variable for Gmail
        String host = "smtp.gmail.com";
        String from = "chitrakshasobalkar@gmail.com"; // Change accordingly
        //final String username = "sobalkarmadhavi1574@gmail.com"; // Change accordingly
        //final String password = "Madhu@1603"; // Use the app-specific password generated

        // Get system properties
        Properties properties = System.getProperties();
        System.out.println("Properties: " + properties);

        // Setting important information to properties object
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the session object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("chitrakshasobalkar@gmail.com", "chitraksha@12");
            }
        });

        session.setDebug(true);

        // Compose the message
        MimeMessage m = new MimeMessage(session);

        try {
            m.setFrom(from);
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            m.setSubject(subject);
            m.setText(message);

            // Send the message
            Transport.send(m);
            System.out.println("Sent message successfully....");
            f=true;


        } catch (Exception e) {
            e.printStackTrace();

        }

        return f;

    }
}
