package com.turkcell.inventoryservice.api.controllers;

import com.turkcell.inventoryservice.business.abstracts.UserService;
import com.turkcell.inventoryservice.business.dto.requests.create.CreateUserRequest;
import com.turkcell.inventoryservice.business.dto.requests.update.UpdateUserRequest;
import com.turkcell.inventoryservice.business.dto.responses.create.CreateUserResponse;
import com.turkcell.inventoryservice.business.dto.responses.get.GetAllUsersResponse;
import com.turkcell.inventoryservice.business.dto.responses.get.GetUserResponse;
import com.turkcell.inventoryservice.business.dto.responses.update.UpdateUserResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;

    @GetMapping
    public List<GetAllUsersResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetUserResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse add(@Valid @RequestBody CreateUserRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateUserResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateUserRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
