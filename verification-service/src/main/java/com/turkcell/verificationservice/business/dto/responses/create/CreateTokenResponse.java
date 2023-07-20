package com.turkcell.verificationservice.business.dto.responses.create;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
public class CreateTokenResponse {
    private UUID id;
    private UUID userId;

    private String confirmationToken;

    private Date createdDate;
}

