package com.turkcell.inventoryservice.business.dto.token.requests.create;

import com.turkcell.inventoryservice.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTokenRequest {

    private String confirmationToken;
    private Date createdDate;
    private User user;
}
