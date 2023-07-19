package com.turkcell.emailservice.business.dto.rules;

import com.turkcell.emailservice.repository.EMailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class EMailBusinessRules {
    private final EMailRepository repository;

    public void checkIfEMailExists(UUID id){
        if(!repository.existsById(id)){
            throw new RuntimeException("EMAIL_NOT_EXIST");
        }
    }
}
