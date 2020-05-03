package com.epsilon.accountapi.serviceImplementation;

import com.epsilon.accountapi.dao.PortalUserRepository;
import com.epsilon.accountapi.dto.CreatePortalUserDto;
import com.epsilon.accountapi.dto.UpdatePortalUserDto;
import com.epsilon.accountapi.enumeration.GenericStatusConstant;
import com.epsilon.accountapi.exception.DoesNotExistException;
import com.epsilon.accountapi.model.PortalUser;
import com.epsilon.accountapi.service.AddressService;
import com.epsilon.accountapi.service.PortalUserService;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class PortalUserServiceImpl implements PortalUserService {

    private static final Logger logger = LoggerFactory.getLogger(PortalUserServiceImpl.class);

    private PortalUserRepository portalUserRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private AddressService addressService;

    @Autowired
    PortalUserServiceImpl(
            @Qualifier("portalUserRepo")PortalUserRepository portalUserRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            AddressService addressService
    ){
        this.portalUserRepository = portalUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.addressService = addressService;
    }

    @Override
    public void createPortalUser(CreatePortalUserDto portalUserDto) {
        PortalUser portalUser = new PortalUser();
        portalUser.setFirstName(portalUserDto.getFirstName().trim());
        portalUser.setEmail(portalUserDto.getEmail().trim());
        portalUser.setLastName(portalUserDto.getLastName().trim());
        portalUser.setMiddleName(portalUserDto.getMiddleName().trim());
        portalUser.setGender(portalUserDto.getGender());
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber phoneNumber = phoneUtil.parse(portalUserDto.getPhoneNumber().trim(), "NGR");
            portalUser.setPhoneNumber(phoneUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL));
        } catch (NumberParseException e) {
            logger.error(e.getMessage());
        }
        portalUser.setCreatedDate(Date.from(Instant.now()));
        portalUser.setLastUpdated(Date.from(Instant.now()));
        portalUser.setStatus(GenericStatusConstant.ACTIVE);
        portalUser.setPassword(bCryptPasswordEncoder.encode(portalUserDto.getPassword().trim()));
        portalUser.setAddress(addressService.createPortalUserAddress(portalUserDto));
        portalUserRepository.save(portalUser);
    }

    @Override
    public void updatePortalUser(Long id, UpdatePortalUserDto portalUserDto) throws DoesNotExistException {
        Optional<PortalUser> optionalPortalUser = portalUserRepository.findById(id);
        if (optionalPortalUser.isPresent()){
            PortalUser portalUser = optionalPortalUser.get();
            if (portalUser.getStatus().equals(GenericStatusConstant.ACTIVE)) {
                portalUser.setAddress(addressService.updatePortalUserAddress(portalUser.getAddress(), portalUserDto));
                portalUser.setFirstName(portalUserDto.getFirstName().trim());
                portalUser.setLastName(portalUserDto.getLastName().trim());
                portalUser.setMiddleName(portalUserDto.getMiddleName().trim());
                portalUser.setLastUpdated(Date.from(Instant.now()));
                portalUser.setGender(portalUserDto.getGender());
                PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
                try {
                    Phonenumber.PhoneNumber phoneNumber = phoneUtil.parse(portalUserDto.getPhoneNumber().trim(), "NGR");
                    portalUser.setPhoneNumber(phoneUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL));
                } catch (NumberParseException e) {
                    logger.error(e.getMessage());
                }
                portalUser.setPassword(bCryptPasswordEncoder.encode(portalUserDto.getPassword().trim()));
                portalUserRepository.save(portalUser);
            }else {
                throw new DoesNotExistException(String.format("User with email %s has been deleted", portalUser.getEmail()));
            }
        }else {
            throw new DoesNotExistException("The user does not exist.");
        }
    }

    @Override
    public Optional<PortalUser> getPortalUserByEmail(String portalUserEmail) {
        return portalUserRepository.findPortalUserByEmail(portalUserEmail);
    }

    @Override
    public void deletePortalUser(long portalUserId) throws DoesNotExistException {
        Optional<PortalUser> portalUser = portalUserRepository.findById(portalUserId);
        if (portalUser.isPresent() && portalUser.get().getStatus().equals(GenericStatusConstant.ACTIVE)){
            portalUser.get().setStatus(GenericStatusConstant.DEACTIVATED);
            portalUserRepository.save(portalUser.get());
        }else {
            throw new DoesNotExistException("User does not exist or has been deleted.");
        }
    }

    @Override
    public Optional<PortalUser> getPortalUserById(long portalUserId) {
        return portalUserRepository.findById(portalUserId);
    }
}
