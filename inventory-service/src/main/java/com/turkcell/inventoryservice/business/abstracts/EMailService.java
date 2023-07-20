package com.turkcell.inventoryservice.business.abstracts;


import com.turkcell.inventoryservice.business.dto.email.requests.create.CreateEMailRequest;
import com.turkcell.inventoryservice.business.dto.email.requests.update.UpdateEMailRequest;
import com.turkcell.inventoryservice.business.dto.email.responses.create.CreateEMailResponse;
import com.turkcell.inventoryservice.business.dto.email.responses.get.GetAllEMailsResponse;
import com.turkcell.inventoryservice.business.dto.email.responses.get.GetEMailResponse;
import com.turkcell.inventoryservice.business.dto.email.responses.update.UpdateEMailResponse;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;
import java.util.UUID;

public interface EMailService {
    List<GetAllEMailsResponse> getAll();
    GetEMailResponse getById(UUID id);
    CreateEMailResponse add(CreateEMailRequest request);
    UpdateEMailResponse update(UUID id, UpdateEMailRequest request);
    void delete(UUID id);
    void sendEmail(SimpleMailMessage email);
}
