package com.turkcell.inventoryservice.business.abstracts;

import com.turkcell.inventoryservice.business.dto.user.requests.create.CreateUserRequest;
import com.turkcell.inventoryservice.business.dto.user.requests.update.UpdateUserRequest;
import com.turkcell.inventoryservice.business.dto.user.responses.create.CreateUserResponse;
import com.turkcell.inventoryservice.business.dto.user.responses.get.GetAllUsersResponse;
import com.turkcell.inventoryservice.business.dto.user.responses.get.GetUserResponse;
import com.turkcell.inventoryservice.business.dto.user.responses.update.UpdateUserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<GetAllUsersResponse> getAll();
    GetUserResponse getById(UUID id);
    CreateUserResponse add(CreateUserRequest request);
    UpdateUserResponse update(UUID id, UpdateUserRequest request);
    void delete(UUID id);

}
