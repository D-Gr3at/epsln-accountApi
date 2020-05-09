package com.epsilon.accountapi.controller;

import com.epsilon.accountapi.dto.CreatePortalUserDto;
import com.epsilon.accountapi.dto.UpdatePortalUserDto;
import com.epsilon.accountapi.exception.DoesNotExistException;
import com.epsilon.accountapi.model.PortalUser;
import com.epsilon.accountapi.service.PortalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/account")
@RestController
@CrossOrigin(value = "*")
public class PortalUserController {

    PortalUserService portalUserService;

    @Autowired
    PortalUserController(PortalUserService portalUserService){
        this.portalUserService = portalUserService;
    }

    @GetMapping("/sign-up")
    public ResponseEntity<?> createPortalUser(@Valid @RequestBody CreatePortalUserDto portalUserDto){
        portalUserService.getPortalUserByEmail(portalUserDto.getEmail()).ifPresent(portalUser -> {
            try {
                throw new DoesNotExistException(String.format("User with email %s already exist", portalUser.getEmail()));
            } catch (DoesNotExistException e) {
                e.printStackTrace();
            }
        });
        portalUserService.createPortalUser(portalUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePortalUser(@PathVariable("id") long id, @Valid @RequestBody UpdatePortalUserDto portalUserDto){
        try {
            portalUserService.updatePortalUser(id, portalUserDto);
        } catch (DoesNotExistException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<PortalUser> getPortalUser(@PathVariable("id") long id) {
        PortalUser portalUser = null;
        try {
            portalUser = portalUserService.getPortalUserById(id).orElseThrow(DoesNotExistException::new);
        } catch (DoesNotExistException e) {
            e.printStackTrace();
        }
        assert portalUser != null;
        return ResponseEntity.ok(portalUser);
    }

    @DeleteMapping(path = "/user/{id}")
    public void deletePortalUser(@PathVariable("id") long id){
        portalUserService.deletePortalUser(id);
    }
}
