package com.turkcell.inventoryservice.business.rules;


import com.turkcell.inventoryservice.repository.EMailRepository;
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
