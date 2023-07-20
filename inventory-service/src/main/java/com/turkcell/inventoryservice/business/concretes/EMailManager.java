package com.turkcell.inventoryservice.business.concretes;

import com.turkcell.inventoryservice.business.abstracts.EMailService;
import com.turkcell.inventoryservice.business.dto.email.requests.create.CreateEMailRequest;
import com.turkcell.inventoryservice.business.dto.email.requests.update.UpdateEMailRequest;
import com.turkcell.inventoryservice.business.dto.email.responses.create.CreateEMailResponse;
import com.turkcell.inventoryservice.business.dto.email.responses.get.GetAllEMailsResponse;
import com.turkcell.inventoryservice.business.dto.email.responses.get.GetEMailResponse;
import com.turkcell.inventoryservice.business.dto.email.responses.update.UpdateEMailResponse;
import com.turkcell.inventoryservice.business.rules.EMailBusinessRules;
import com.turkcell.inventoryservice.entities.EMail;
import com.turkcell.inventoryservice.repository.EMailRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EMailManager implements EMailService {
    private final EMailRepository repository;
    private final EMailBusinessRules rules;
    private final ModelMapper mapper;
    private JavaMailSender javaMailSender;

    @Override
    public List<GetAllEMailsResponse> getAll() {
        var emails = repository.findAll();
        var response = emails
                .stream()
                .map(email -> mapper.map(email, GetAllEMailsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetEMailResponse getById(UUID id) {
        rules.checkIfEMailExists(id);
        var email = repository.findById(id).orElseThrow();
        var response = mapper.map(email, GetEMailResponse.class);

        return response;
    }

    @Override
    public CreateEMailResponse add(CreateEMailRequest request) {
        var email = mapper.map(request, EMail.class);
        email.setId(UUID.randomUUID());
        repository.save(email);
        var response = mapper.map(email, CreateEMailResponse.class);

        return response;
    }

    @Override
    public UpdateEMailResponse update(UUID id, UpdateEMailRequest request) {
        rules.checkIfEMailExists(id);
        var email = mapper.map(request, EMail.class);
        email.setId(id);
        repository.save(email);
        var response = mapper.map(email, UpdateEMailResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfEMailExists(id);
        repository.deleteById(id);
    }

    @Override
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

}
