package com.epsilon.accountapi.config;

import com.epsilon.accountapi.dao.PermissionRepository;
import com.epsilon.accountapi.dao.PortalUserRepository;
import com.epsilon.accountapi.dao.RoleRepository;
import com.epsilon.accountapi.enumeration.GenderConstant;
import com.epsilon.accountapi.enumeration.GenericStatusConstant;
import com.epsilon.accountapi.model.Address;
import com.epsilon.accountapi.model.Permission;
import com.epsilon.accountapi.model.PortalUser;
import com.epsilon.accountapi.model.Role;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@PropertySource(value = "classpath:application.properties")
public class AppInitializer {

    private PortalUserRepository portalUserRepository;

    private PasswordEncoder passwordEncoder;

    private RoleRepository roleRepository;

    private PermissionRepository permissionRepository;

    private static final Map<List<String>, List<String>> initialRoles = ImmutableMap.<List<String>, List<String>>builder()
            .put(Stream.of("ADMIN").collect(Collectors.toList()), Stream.of("CREATE", "READ", "UPDATE", "DELETE").collect(Collectors.toList()))
            .put(Stream.of("USER").collect(Collectors.toList()), Stream.of("CREATE", "READ", "UPDATE").collect(Collectors.toList())).build();

    @Autowired
    public AppInitializer(
            PortalUserRepository portalUserRepository,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository,
            PermissionRepository permissionRepository
    ) {
        this.passwordEncoder = passwordEncoder;
        this.portalUserRepository = portalUserRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        initializeRoles();
        initializeUser(Stream.of("ADMIN", "USER").collect(Collectors.toList()));
        //initializeUser("USER", "user");
    }

    private void initializeRoles() {
        initialRoles.forEach((roles, permissions) -> {
            Role role = new Role();
            roles.forEach(r->{
                role.setName(r);
                if (roleRepository.findByName(r) == null){
                    roleRepository.save(role);
                }
                permissions.forEach(p -> {
                    Permission permission = new Permission();
                    System.out.println("role======"+r+"permission====="+p);
                    permission.setName(p);
                    if (permissionRepository.findByName(p) == null) {
                        permissionRepository.save(permission);
                    }
                });
            });
//            if (roleRepository.findByNameIn(roles) == null) {
//            }
        });
    }

    private void initializeUser(List<String> roleNames) {
        System.out.println(roleNames);
        Optional<PortalUser> u = portalUserRepository.findPortalUserByEmail("billeibinabo@testing.com");
        if (!u.isPresent()) {
            PortalUser portalUser = new PortalUser();
            Address address = new Address();
            portalUser.setFirstName("Ibinabo");
            portalUser.setMiddleName("Tonte");
            portalUser.setLastName("Bille");
            portalUser.setEmail("billeibinabo@testing.com");
            portalUser.setStatus(GenericStatusConstant.ACTIVE);
            portalUser.setPassword(passwordEncoder.encode("Password1"));
            portalUser.setPhoneNumber("08162530944");
            portalUser.setGender(GenderConstant.MALE);
            portalUser.setLastUpdated(Date.from(Instant.now()));
            portalUser.setCreatedDate(Date.from(Instant.now()));
            address.setHouseNumber("A75");
            address.setStreet("Saraha Fyne Stone Estate, Gwarinpa");
            address.setCity("Abuja");
            address.setState("Federal Capital Territory");
            address.setCountry("Nigeria");
            address.setZip("900108");
            portalUser.setAddress(address);
            List<Role> roles = roleRepository.findByNameIn(roleNames);
            roles.forEach(role -> {
                //System.out.println(role.getName());
                role.setPermissions(permissionRepository.findPermissionByRole(role.getName()));
                System.out.println(permissionRepository.findPermissionByRole(role.getName()));
            });
            System.out.println(roles);
            portalUser.setRoles(roles);
            portalUserRepository.save(portalUser);
        }
    }
}
