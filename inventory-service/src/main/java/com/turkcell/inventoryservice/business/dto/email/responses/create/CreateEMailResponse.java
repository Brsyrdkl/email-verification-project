package com.turkcell.inventoryservice.business.dto.email.responses.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEMailResponse {

    private UUID id;
    private UUID userId;
    private String email;

    private String userUsername;
}
