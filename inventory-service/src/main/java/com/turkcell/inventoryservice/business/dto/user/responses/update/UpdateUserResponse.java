package com.turkcell.inventoryservice.business.dto.user.responses.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserResponse {
    private String id;
    private String tokenId;

    private String emailId;
    private String username;

    private String firstName;

    private String lastName;

    private String password;

    private boolean isEnabled;

}