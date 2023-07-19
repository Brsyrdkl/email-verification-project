package com.turkcell.inventoryservice.business.concretes;

import com.turkcell.inventoryservice.business.abstracts.UserService;
import com.turkcell.inventoryservice.business.dto.requests.create.CreateUserRequest;
import com.turkcell.inventoryservice.business.dto.requests.update.UpdateUserRequest;
import com.turkcell.inventoryservice.business.dto.responses.create.CreateUserResponse;
import com.turkcell.inventoryservice.business.dto.responses.get.GetAllUsersResponse;
import com.turkcell.inventoryservice.business.dto.responses.get.GetUserResponse;
import com.turkcell.inventoryservice.business.dto.responses.update.UpdateUserResponse;
import com.turkcell.inventoryservice.business.rules.UserBusinessRules;
import com.turkcell.inventoryservice.entities.User;
import com.turkcell.inventoryservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserManager implements UserService {

    private final UserRepository repository;
    private final UserBusinessRules rules;
    private final ModelMapper mapper;

    @Override
    public List<GetAllUsersResponse> getAll() {
        var users = repository.findAll();
        var response = users
                .stream()
                .map(user -> mapper.map(user, GetAllUsersResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetUserResponse getById(UUID id) {
        rules.checkIfUserExists(id);
        var user = repository.findById(id).orElseThrow();
        var response = mapper.map(user, GetUserResponse.class);

        return response;
    }

    @Override
    public CreateUserResponse add(CreateUserRequest request) {
        var user = mapper.map(request, User.class);
        user.setId(UUID.randomUUID());
        repository.save(user);
        var response = mapper.map(user, CreateUserResponse.class);

        return response;
    }

    @Override
    public UpdateUserResponse update(UUID id, UpdateUserRequest request) {
        rules.checkIfUserExists(id);
        var user = mapper.map(request, User.class);
        user.setId(id);
        repository.save(user);
        var response = mapper.map(user, UpdateUserResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfUserExists(id);
        repository.deleteById(id);
    }
}
