package com.turkcell.inventoryservice.business.dto.token.responses.get;

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
public class GetTokenResponse {
    private UUID id;
    private UUID userId;
    private String confirmationToken;

    private Date createdDate;
}
