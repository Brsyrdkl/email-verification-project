package com.turkcell.inventoryservice.api.controllers;


import com.turkcell.inventoryservice.business.abstracts.TokenService;
import com.turkcell.inventoryservice.business.dto.token.requests.create.CreateTokenRequest;
import com.turkcell.inventoryservice.business.dto.token.requests.update.UpdateTokenRequest;
import com.turkcell.inventoryservice.business.dto.token.responses.create.CreateTokenResponse;
import com.turkcell.inventoryservice.business.dto.token.responses.get.GetAllTokensResponse;
import com.turkcell.inventoryservice.business.dto.token.responses.get.GetTokenResponse;
import com.turkcell.inventoryservice.business.dto.token.responses.update.UpdateTokenResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tokens")
public class TokenController {
    private final TokenService service;

    @GetMapping
    public List<GetAllTokensResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetTokenResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateTokenResponse add(@Valid @RequestBody CreateTokenRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateTokenResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateTokenRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
