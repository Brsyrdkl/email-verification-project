package com.turkcell.inventoryservice.repository;

import com.turkcell.inventoryservice.entities.EMail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EMailRepository extends JpaRepository<EMail, UUID> {

    EMail findEMailByUserId(UUID userId);
}
