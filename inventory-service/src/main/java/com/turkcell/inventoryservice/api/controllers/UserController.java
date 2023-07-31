package com.turkcell.inventoryservice.api.controllers;

import com.turkcell.inventoryservice.business.abstracts.UserService;
import com.turkcell.inventoryservice.business.dto.user.requests.create.CreateUserRequest;
import com.turkcell.inventoryservice.business.dto.user.requests.update.UpdateUserRequest;
import com.turkcell.inventoryservice.business.dto.user.responses.create.CreateUserResponse;
import com.turkcell.inventoryservice.business.dto.user.responses.get.GetAllUsersResponse;
import com.turkcell.inventoryservice.business.dto.user.responses.get.GetUserResponse;
import com.turkcell.inventoryservice.business.dto.user.responses.update.UpdateUserResponse;
import com.turkcell.inventoryservice.entities.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/register/{id}")
    public ResponseEntity<?> registerUser(@PathVariable UUID id) {
        return service.sendMailMessage(id);
    }

    @RequestMapping(value="/confirm-account/{id}", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@PathVariable UUID id){
        return service.confirmEmail(id);
    }
}
