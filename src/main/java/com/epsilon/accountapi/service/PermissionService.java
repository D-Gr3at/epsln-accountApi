package com.epsilon.accountapi.service;

import com.epsilon.accountapi.model.Permission;

import java.util.List;

public interface PermissionService {

    Permission createPermission(Permission permission);

    Permission updatePermission(Permission permission);

    Long deletePermission(Long id);

    List<Permission> getPermissions();

    Permission getPermission(Long id);

    Permission findByName(String name);
}
