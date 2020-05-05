package com.epsilon.accountapi.service;

import com.epsilon.accountapi.model.Role;

import java.util.List;

public interface RoleService {

    Role createRole(Role role);

    Role updateRole(Role role);

    Long deleteRole(Long id);

    List<Role> getRoles();

    Role getRole(Long id);

    Role findByName(String name);
}
