package com.turkcell.inventoryservice.business.concretes;

import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.turkcell.inventoryservice.business.abstracts.UserService;
import com.turkcell.inventoryservice.business.dto.requests.create.CreateUserRequest;
import com.turkcell.inventoryservice.business.dto.requests.update.UpdateUserRequest;
import com.turkcell.inventoryservice.business.dto.responses.create.CreateUserResponse;
import com.turkcell.inventoryservice.business.dto.responses.get.GetAllUsersResponse;
import com.turkcell.inventoryservice.business.dto.responses.get.GetUserResponse;
import com.turkcell.inventoryservice.business.dto.responses.update.UpdateUserResponse;
import com.turkcell.inventoryservice.entities.User;
import com.turkcell.inventoryservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserManager implements UserService {

    private final UserRepository repository;
    private final ModelMapperService mapper;

    @Override
    public List<GetAllUsersResponse> getAll() {
        var users = repository.findAll();
        var response = users
                .stream()
                .map(user -> mapper.forResponse().map(user, GetAllUsersResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetUserResponse getById(UUID id) {
        //TODO: business rules
        var user = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(user, GetUserResponse.class);

        return response;
    }

    @Override
    public CreateUserResponse add(CreateUserRequest request) {
        var user = mapper.forRequest().map(request, User.class);
        repository.save(user);
        var response = mapper.forResponse().map(user, CreateUserResponse.class);

        return response;
    }

    @Override
    public UpdateUserResponse update(UUID id, UpdateUserRequest request) {
        //TODO: business rules
        var user = mapper.forRequest().map(request, User.class);
        user.setId(id);
        repository.save(user);
        var response = mapper.forResponse().map(user, UpdateUserResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        //TODO: business rules
        repository.deleteById(id);
    }
}
