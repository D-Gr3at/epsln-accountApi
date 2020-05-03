package com.epsilon.accountapi.service;

import com.epsilon.accountapi.dto.CreatePortalUserDto;
import com.epsilon.accountapi.dto.UpdatePortalUserDto;
import com.epsilon.accountapi.exception.DoesNotExistException;
import com.epsilon.accountapi.model.PortalUser;

import java.util.Optional;

public interface PortalUserService {
    void createPortalUser(CreatePortalUserDto portalUserDto);

    void updatePortalUser(Long id, UpdatePortalUserDto portalUserDto) throws DoesNotExistException;

    Optional<PortalUser> getPortalUserByEmail(String portalUserEmail);

    Optional<PortalUser> getPortalUserById(long portalUserId);

    void deletePortalUser(long portalUserId) throws DoesNotExistException;
}
