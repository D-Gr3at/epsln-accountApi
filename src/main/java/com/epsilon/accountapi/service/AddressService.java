package com.epsilon.accountapi.service;

import com.epsilon.accountapi.dto.CreatePortalUserDto;
import com.epsilon.accountapi.dto.UpdatePortalUserDto;
import com.epsilon.accountapi.model.Address;

public interface AddressService {

    Address createPortalUserAddress(CreatePortalUserDto portalUserDto);

    Address updatePortalUserAddress(Address address, UpdatePortalUserDto portalUserDto);
}
