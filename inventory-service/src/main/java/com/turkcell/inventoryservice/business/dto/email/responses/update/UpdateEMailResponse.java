package com.turkcell.inventoryservice.business.dto.email.responses.update;

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
public class UpdateEMailResponse {

    private String id;


    private String name;

    private String userId;
}
