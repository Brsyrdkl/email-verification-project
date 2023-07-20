package com.turkcell.inventoryservice.api.controllers;

import com.turkcell.emailservice.business.abstracts.EMailService;
import com.turkcell.emailservice.business.dto.requests.create.CreateEMailRequest;
import com.turkcell.emailservice.business.dto.requests.update.UpdateEMailRequest;
import com.turkcell.emailservice.business.dto.responses.create.CreateEMailResponse;
import com.turkcell.emailservice.business.dto.responses.get.GetAllEMailsResponse;
import com.turkcell.emailservice.business.dto.responses.get.GetEMailResponse;
import com.turkcell.emailservice.business.dto.responses.update.UpdateEMailResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/emails")
public class EMailController {
    private final EMailService service;

    @GetMapping
    public List<GetAllEMailsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetEMailResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateEMailResponse add(@Valid @RequestBody CreateEMailRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateEMailResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateEMailRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
