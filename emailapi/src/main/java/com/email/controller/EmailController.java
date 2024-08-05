package com.email.controller;


import com.email.model.EmailRequest;
import com.email.model.EmailResponse;
import com.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/sendemail",method = RequestMethod.POST)
    public ResponseEntity<EmailResponse> sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            emailService.sendEmail(emailRequest.getSubject(),emailRequest.getMessage(), emailRequest.getTo());
            EmailResponse response = new EmailResponse("Email sent successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            EmailResponse response = new EmailResponse("Email is not sent.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
