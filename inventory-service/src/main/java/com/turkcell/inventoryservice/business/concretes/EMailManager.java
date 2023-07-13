package com.turkcell.inventoryservice.business.concretes;

import com.turkcell.inventoryservice.business.abstracts.EMailService;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EMailManager implements EMailService {
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(SimpleMailMessage email){
        javaMailSender.send(email);
    }
}
