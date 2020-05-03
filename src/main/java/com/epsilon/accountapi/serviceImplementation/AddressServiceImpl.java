package com.epsilon.accountapi.serviceImplementation;

import com.epsilon.accountapi.dto.CreatePortalUserDto;
import com.epsilon.accountapi.dto.UpdatePortalUserDto;
import com.epsilon.accountapi.model.Address;
import com.epsilon.accountapi.service.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Override
    public Address createPortalUserAddress(CreatePortalUserDto portalUserDto) {
        Address portalUserAddress = new Address();
        portalUserAddress.setZip(portalUserDto.getZip().trim());
        portalUserAddress.setStreet(portalUserDto.getStreet().trim());
        portalUserAddress.setState(portalUserDto.getState().trim());
        portalUserAddress.setHouseNumber(portalUserDto.getHouseNumber());
        portalUserAddress.setCountry(portalUserDto.getCountry());
        portalUserAddress.setCity(portalUserDto.getCity().trim());
        return portalUserAddress;
    }

    @Override
    public Address updatePortalUserAddress(Address updateAddress, UpdatePortalUserDto portalUserDto) {
        updateAddress.setCity(portalUserDto.getCity().trim());
        updateAddress.setCountry(portalUserDto.getCountry().trim());
        updateAddress.setHouseNumber(portalUserDto.getHouseNumber());
        updateAddress.setState(portalUserDto.getState().trim());
        updateAddress.setStreet(portalUserDto.getStreet().trim());
        updateAddress.setZip(portalUserDto.getZip().trim());
        return updateAddress;
    }
}
