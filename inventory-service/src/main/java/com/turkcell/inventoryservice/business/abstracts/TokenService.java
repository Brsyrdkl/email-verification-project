package com.turkcell.inventoryservice.business.abstracts;


import com.turkcell.inventoryservice.business.dto.token.requests.create.CreateTokenRequest;
import com.turkcell.inventoryservice.business.dto.token.requests.update.UpdateTokenRequest;
import com.turkcell.inventoryservice.business.dto.token.responses.create.CreateTokenResponse;
import com.turkcell.inventoryservice.business.dto.token.responses.get.GetAllTokensResponse;
import com.turkcell.inventoryservice.business.dto.token.responses.get.GetTokenResponse;
import com.turkcell.inventoryservice.business.dto.token.responses.update.UpdateTokenResponse;

import java.util.List;
import java.util.UUID;

public interface TokenService {
    List<GetAllTokensResponse> getAll();
    GetTokenResponse getById(UUID id);
    CreateTokenResponse add(CreateTokenRequest request);
    UpdateTokenResponse update(UUID id, UpdateTokenRequest request);
    void delete(UUID id);
}
