package com.turkcell.inventoryservice.business.abstracts;

import org.springframework.mail.SimpleMailMessage;

public interface EMailService {
    void sendEmail(SimpleMailMessage email);
}
