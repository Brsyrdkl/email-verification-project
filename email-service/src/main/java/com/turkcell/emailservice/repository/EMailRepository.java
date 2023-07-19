package com.turkcell.emailservice.repository;

import com.turkcell.emailservice.entities.EMail;
import com.turkcell.inventoryservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface EMailRepository extends JpaRepository<EMail, UUID> {
}
