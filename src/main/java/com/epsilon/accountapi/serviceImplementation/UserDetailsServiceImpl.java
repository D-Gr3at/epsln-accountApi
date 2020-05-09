package com.epsilon.accountapi.serviceImplementation;

import com.epsilon.accountapi.dao.PortalUserRepository;
import com.epsilon.accountapi.dao.RoleRepository;
import com.epsilon.accountapi.model.PortalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private PortalUserRepository portalUserRepository;

    private RoleRepository roleRepository;

    @Autowired
    public UserDetailsServiceImpl(
            PortalUserRepository portalUserRepository,
            RoleRepository roleRepository
    ) {
        this.portalUserRepository = portalUserRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        Optional<PortalUser> optionalPortalUser = portalUserRepository.findPortalUserByEmail(username);
        if (!optionalPortalUser.isPresent()){
            throw new UsernameNotFoundException(username);
        }
        PortalUser portalUser = optionalPortalUser.get();
        return new User(portalUser.getUsername(), portalUser.getPassword(), portalUser.getAuthorities());
    }
}
