package com.turkcell.verificationservice.repository;

import com.turkcell.verificationservice.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, UUID> {
}
