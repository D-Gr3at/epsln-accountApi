package com.epsilon.accountapi.service;

import com.epsilon.accountapi.model.Permission;
import com.epsilon.accountapi.model.Role;

import java.util.Collection;
import java.util.List;

public interface RoleService {

    Role createRole(String roleName, Collection<Permission> permissions);

    Role updateRole(Role role);

    Long deleteRole(Long id);

    List<Role> getRoles();

    Role getRole(Long id);

    Role findByName(String name);
}
