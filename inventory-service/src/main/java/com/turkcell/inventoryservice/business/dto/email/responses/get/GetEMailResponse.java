package com.turkcell.inventoryservice.business.dto.email.responses.get;

import com.turkcell.inventoryservice.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetEMailResponse {
    private String id;

    private String name;
    private String userId;
}
