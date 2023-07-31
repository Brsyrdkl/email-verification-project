package com.turkcell.inventoryservice.repository;

import com.turkcell.inventoryservice.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, UUID> {

    Token findTokenByUserId(UUID userId);

}
