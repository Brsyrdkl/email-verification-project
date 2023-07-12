package com.turkcell.inventoryservice.business.dto.requests.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    private String username;

    private String email;

    private String password;

}
