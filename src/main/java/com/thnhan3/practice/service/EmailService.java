package com.thnhan3.practice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    @Async
    public void sendReservationConfirmationEmail(String to, String subject, String body) {
        log.info("sending email  to {}, subject {}",  to, subject);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        log.info("send email successfully");
    }
}
