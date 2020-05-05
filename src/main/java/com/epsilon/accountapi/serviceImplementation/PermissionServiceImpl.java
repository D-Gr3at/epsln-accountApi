package com.epsilon.accountapi.serviceImplementation;

import com.epsilon.accountapi.dao.PermissionRepository;
import com.epsilon.accountapi.model.Permission;
import com.epsilon.accountapi.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    private PermissionRepository permissionRepository;

    @Autowired
    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission updatePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Long deletePermission(Long id) {
        permissionRepository.deleteById(id);
        return id;
    }

    @Override
    public List<Permission> getPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission getPermission(Long id) {
        return permissionRepository.findById(id).orElse(null);
    }

    @Override
    public Permission findByName(String name) {
        return permissionRepository.findByName(name);
    }
}
