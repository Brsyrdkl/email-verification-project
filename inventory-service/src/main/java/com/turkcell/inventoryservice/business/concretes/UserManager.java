package com.turkcell.inventoryservice.business.concretes;

import com.turkcell.commonpackageemail.utils.mappers.ModelMapperService;
import com.turkcell.inventoryservice.business.abstracts.EMailService;
import com.turkcell.inventoryservice.business.abstracts.TokenService;
import com.turkcell.inventoryservice.business.abstracts.UserService;
import com.turkcell.inventoryservice.business.dto.user.requests.create.CreateUserRequest;
import com.turkcell.inventoryservice.business.dto.user.requests.update.UpdateUserRequest;
import com.turkcell.inventoryservice.business.dto.user.responses.create.CreateUserResponse;
import com.turkcell.inventoryservice.business.dto.user.responses.get.GetAllUsersResponse;
import com.turkcell.inventoryservice.business.dto.user.responses.get.GetUserResponse;
import com.turkcell.inventoryservice.business.dto.user.responses.update.UpdateUserResponse;
import com.turkcell.inventoryservice.business.rules.UserBusinessRules;
import com.turkcell.inventoryservice.entities.EMail;
import com.turkcell.inventoryservice.entities.Token;
import com.turkcell.inventoryservice.entities.User;;
import com.turkcell.inventoryservice.repository.EMailRepository;
import com.turkcell.inventoryservice.repository.TokenRepository;
import com.turkcell.inventoryservice.repository.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserManager implements UserService {

    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final EMailRepository eMailRepository;
    private final EMailService eMailService;
    private final TokenService tokenService;
    private final UserBusinessRules rules;
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
        rules.checkIfUserExists(id);
        var user = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(user, GetUserResponse.class);

        return response;
    }

    @Override
    public CreateUserResponse add(CreateUserRequest request) {
        var user = mapper.forRequest().map(request, User.class);
        user.setEnabled(false);
        user.setId(UUID.randomUUID());
        repository.save(user);
        var response = mapper.forResponse().map(user, CreateUserResponse.class);

        return response;
    }

    @Override
    public UpdateUserResponse update(UUID id, UpdateUserRequest request) {
        rules.checkIfUserExists(id);
        var user = mapper.forRequest().map(request, User.class);
        user.setId(id);
        repository.save(user);
        var response = mapper.forResponse().map(user, UpdateUserResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfUserExists(id);
        repository.deleteById(id);
    }
    @Override
    public ResponseEntity<?> sendMailMessage(UUID userId){

        Token token = tokenRepository.findTokenByUserId(userId);
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(eMailRepository.findEMailByUserId(userId).getName());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:9011/inventory-service/api/users/confirm-account/"+userId);
        eMailService.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + token.getId());

        return ResponseEntity.ok("Verify email by the link sent on your email address");

    }
    @Override
    public ResponseEntity<?> confirmEmail(UUID userId) {
        Token token = tokenRepository.findTokenByUserId(userId);

        if(token != null)
        {
            User user = repository.findUserById(userId);
            user.setEnabled(true);
            repository.save(user);
            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }
}
