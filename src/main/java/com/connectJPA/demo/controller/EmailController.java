package com.connectJPA.demo.controller;

import com.connectJPA.demo.entity.Subscriber;
import com.connectJPA.demo.repository.SubscriberRepository;
import com.connectJPA.demo.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class EmailController {

    private final EmailService emailService;
    private final SubscriberRepository subscriberRepository;

    @PostMapping("/subscribe")
    public String subscribe(@RequestBody Subscriber subscriber) {
        if (subscriberRepository.findByEmail(subscriber.getEmail()).isEmpty()) {
            subscriberRepository.save(subscriber);
        }
        try {
            emailService.sendSubscriptionConfirmation(subscriber.getEmail());
            return "Subscribe success! Check your email.";
        } catch (MessagingException e) {
            return "Email sent Error: " + e.getMessage();
        }
    }

    @PostMapping("/send-voucher")
    public String sendVoucher(@RequestParam String voucherCode) {
        try {
            emailService.sendVoucherToSubscribers(voucherCode);
            return "Voucher sent all customer!";
        } catch (MessagingException e) {
            return "Voucher sent Error: " + e.getMessage();
        }
    }

    @PostMapping("/send")
    public String sendMail(@RequestParam String to,
                           @RequestParam String subject,
                           @RequestParam String text) {
        try {
            emailService.sendEmail(to, subject, text);
            return "Email sent successfully!";
        } catch (MessagingException e) {
            return "Failed to send email: " + e.getMessage();
        }
    }
}

