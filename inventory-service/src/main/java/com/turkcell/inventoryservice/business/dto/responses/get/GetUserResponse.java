package com.turkcell.inventoryservice.business.dto.responses.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserResponse {
    private UUID id;

    private String username;

    private String firstName;

    private String lastName;

    private String password;

    private boolean isEnabled;

}
