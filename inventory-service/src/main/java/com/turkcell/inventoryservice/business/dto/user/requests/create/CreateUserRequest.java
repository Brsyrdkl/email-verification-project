package com.turkcell.inventoryservice.business.dto.user.requests.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    private UUID tokenId;

    private UUID emailId;

    private String username;

    private String firstName;

    private String lastName;

    private String password;

    private boolean isEnabled;

}

