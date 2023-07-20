package com.turkcell.inventoryservice.repository;

import com.turkcell.inventoryservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUserEmailIgnoreCase(UUID emailId);
    User findByUserTokenIgnoreCase(UUID tokenId);



}
