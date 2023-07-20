package com.turkcell.inventoryservice.business.concretes;

import com.turkcell.verificationservice.business.abstracts.TokenService;
import com.turkcell.verificationservice.business.dto.requests.create.CreateTokenRequest;
import com.turkcell.verificationservice.business.dto.requests.update.UpdateTokenRequest;
import com.turkcell.verificationservice.business.dto.responses.create.CreateTokenResponse;
import com.turkcell.verificationservice.business.dto.responses.get.GetAllTokensResponse;
import com.turkcell.verificationservice.business.dto.responses.get.GetTokenResponse;
import com.turkcell.verificationservice.business.dto.responses.update.UpdateTokenResponse;
import com.turkcell.verificationservice.entities.Token;
import com.turkcell.verificationservice.repository.TokenRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TokenManager implements TokenService {

    private final TokenRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllTokensResponse> getAll() {
        var tokens = repository.findAll();
        var response = tokens
                .stream()
                .map(token -> mapper.map(token, GetAllTokensResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetTokenResponse getById(UUID id) {
        //TODO: business rules
        var token = repository.findById(id).orElseThrow();
        var response = mapper.map(token, GetTokenResponse.class);

        return response;
    }

    @Override
    public CreateTokenResponse add(CreateTokenRequest request) {
        var token = mapper.map(request, Token.class);
        repository.save(token);
        var response = mapper.map(token, CreateTokenResponse.class);

        return response;
    }

    @Override
    public UpdateTokenResponse update(UUID id, UpdateTokenRequest request) {
        //TODO: business rules
        var token = mapper.map(request, Token.class);
        token.setId(id);
        repository.save(token);
        var response = mapper.map(token, UpdateTokenResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        //TODO: business rules
        repository.deleteById(id);
    }
}

