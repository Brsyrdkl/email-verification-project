package com.turkcell.inventoryservice.business.concretes;


import com.turkcell.commonpackageemail.utils.mappers.ModelMapperService;
import com.turkcell.inventoryservice.business.abstracts.TokenService;
import com.turkcell.inventoryservice.business.dto.token.requests.create.CreateTokenRequest;
import com.turkcell.inventoryservice.business.dto.token.requests.update.UpdateTokenRequest;
import com.turkcell.inventoryservice.business.dto.token.responses.create.CreateTokenResponse;
import com.turkcell.inventoryservice.business.dto.token.responses.get.GetAllTokensResponse;
import com.turkcell.inventoryservice.business.dto.token.responses.get.GetTokenResponse;
import com.turkcell.inventoryservice.business.dto.token.responses.update.UpdateTokenResponse;
import com.turkcell.inventoryservice.entities.Token;
import com.turkcell.inventoryservice.repository.TokenRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TokenManager implements TokenService {

    private final TokenRepository repository;
    private final ModelMapperService mapper;

    @Override
    public List<GetAllTokensResponse> getAll() {
        var tokens = repository.findAll();
        var response = tokens
                .stream()
                .map(token -> mapper.forResponse().map(token, GetAllTokensResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetTokenResponse getById(UUID id) {
        //TODO: business rules
        var token = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(token, GetTokenResponse.class);

        return response;
    }

    @Override
    public CreateTokenResponse add(CreateTokenRequest request) {
        var token = mapper.forRequest().map(request, Token.class);
        token.setId(UUID.randomUUID());
        token.setCreatedDate(LocalDateTime.now());
        repository.save(token);
        var response = mapper.forResponse().map(token, CreateTokenResponse.class);

        return response;
    }

    @Override
    public UpdateTokenResponse update(UUID id, UpdateTokenRequest request) {
        //TODO: business rules
        var token = mapper.forRequest().map(request, Token.class);
        token.setId(id);
        token.setCreatedDate(LocalDateTime.now());
        repository.save(token);
        var response = mapper.forResponse().map(token, UpdateTokenResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        //TODO: business rules
        repository.deleteById(id);
    }
}

