package com.turkcell.inventoryservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;

    private String firstName;

    private String lastName;

    private String password;

    private boolean isEnabled;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "token_id")
    private Token token;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "email_id")
    private EMail email;

}
