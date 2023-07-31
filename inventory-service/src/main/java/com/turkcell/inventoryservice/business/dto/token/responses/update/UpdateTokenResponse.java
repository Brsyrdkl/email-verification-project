package com.turkcell.inventoryservice.business.dto.token.responses.update;

import com.turkcell.inventoryservice.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTokenResponse {
    private String id;

    private String name;
    private LocalDateTime createdDate;

    private String userId;
}
