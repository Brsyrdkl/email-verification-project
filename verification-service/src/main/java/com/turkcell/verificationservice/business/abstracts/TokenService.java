package com.turkcell.verificationservice.business.abstracts;

import com.turkcell.verificationservice.business.dto.requests.create.CreateTokenRequest;
import com.turkcell.verificationservice.business.dto.requests.update.UpdateTokenRequest;
import com.turkcell.verificationservice.business.dto.responses.create.CreateTokenResponse;
import com.turkcell.verificationservice.business.dto.responses.get.GetAllTokensResponse;
import com.turkcell.verificationservice.business.dto.responses.get.GetTokenResponse;
import com.turkcell.verificationservice.business.dto.responses.update.UpdateTokenResponse;

import java.util.List;
import java.util.UUID;

public interface TokenService {
    List<GetAllTokensResponse> getAll();
    GetTokenResponse getById(UUID id);
    CreateTokenResponse add(CreateTokenRequest request);
    UpdateTokenResponse update(UUID id, UpdateTokenRequest request);
    void delete(UUID id);
}
