package com.turkcell.inventoryservice.repository;

import com.turkcell.emailservice.entities.EMail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EMailRepository extends JpaRepository<EMail, UUID> {

    EMail findEMailById(UUID emailId);
}
