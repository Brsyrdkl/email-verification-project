package com.turkcell.inventoryservice.business.concretes;

import com.turkcell.emailservice.business.abstracts.EMailService;
import com.turkcell.emailservice.repository.EMailRepository;
import com.turkcell.inventoryservice.business.abstracts.UserService;
import com.turkcell.inventoryservice.business.dto.user.requests.create.CreateUserRequest;
import com.turkcell.inventoryservice.business.dto.user.requests.update.UpdateUserRequest;
import com.turkcell.inventoryservice.business.dto.user.responses.create.CreateUserResponse;
import com.turkcell.inventoryservice.business.dto.user.responses.get.GetAllUsersResponse;
import com.turkcell.inventoryservice.business.dto.user.responses.get.GetUserResponse;
import com.turkcell.inventoryservice.business.dto.user.responses.update.UpdateUserResponse;
import com.turkcell.inventoryservice.business.rules.UserBusinessRules;
import com.turkcell.inventoryservice.entities.User;
import com.turkcell.inventoryservice.repository.UserRepository;
import com.turkcell.verificationservice.entities.Token;
import com.turkcell.verificationservice.repository.TokenRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final EMailRepository emailRepository;
    private final EMailService eMailService;
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
        user.setEnabled(false);
        user.setId(UUID.randomUUID());
        sendMailMessage(user);
        confirmEmail(tokenRepository.findTokenById(user.getTokenId()).getConfirmationToken());
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

    public ResponseEntity<?> sendMailMessage(User user){

        Token token = tokenRepository.findTokenById(user.getTokenId());
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(emailRepository.findEMailById(user.getEmailId()).getEmail());;
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8085/confirm-account?token="+token.getConfirmationToken());
        eMailService.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + token.getConfirmationToken());

        return ResponseEntity.ok("Verify email by the link sent on your email address");

    }

    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        Token token = tokenRepository.findByToken(confirmationToken);

        if(token != null)
        {
            User user = repository.findByUserTokenIgnoreCase(token.getId());
            user.setEnabled(true);
            repository.save(user);
            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }
}
