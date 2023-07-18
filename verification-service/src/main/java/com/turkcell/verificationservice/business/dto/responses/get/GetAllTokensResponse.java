package com.turkcell.verificationservice.business.dto.responses.get;

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
public class GetAllTokensResponse {
    private UUID id;

    private String confirmationToken;

    private Date createdDate;
}
