package com.epsilon.accountapi.dao;

import com.epsilon.accountapi.model.PortalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("portalUserRepo")
public interface PortalUserRepository extends JpaRepository<PortalUser, Long> {
    Optional<PortalUser> findPortalUserByEmail(String email);
}
