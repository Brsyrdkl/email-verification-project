package com.turkcell.inventoryservice.business.abstracts;


import com.turkcell.emailservice.business.dto.requests.create.CreateEMailRequest;
import com.turkcell.emailservice.business.dto.requests.update.UpdateEMailRequest;
import com.turkcell.emailservice.business.dto.responses.create.CreateEMailResponse;
import com.turkcell.emailservice.business.dto.responses.get.GetAllEMailsResponse;
import com.turkcell.emailservice.business.dto.responses.get.GetEMailResponse;
import com.turkcell.emailservice.business.dto.responses.update.UpdateEMailResponse;
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
