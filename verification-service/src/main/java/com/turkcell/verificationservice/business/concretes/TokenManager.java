package com.turkcell.verificationservice.business.concretes;

import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
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
import org.springframework.stereotype.Service;

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
        repository.save(token);
        var response = mapper.forResponse().map(token, CreateTokenResponse.class);

        return response;
    }

    @Override
    public UpdateTokenResponse update(UUID id, UpdateTokenRequest request) {
        //TODO: business rules
        var token = mapper.forRequest().map(request, Token.class);
        token.setTokenId(id);
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

