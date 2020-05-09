package com.epsilon.accountapi.serviceImplementation;

import com.epsilon.accountapi.dao.PermissionRepository;
import com.epsilon.accountapi.dao.RoleRepository;
import com.epsilon.accountapi.model.Permission;
import com.epsilon.accountapi.model.Role;
import com.epsilon.accountapi.service.PermissionService;
import com.epsilon.accountapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    private PermissionService permissionService;

    private PermissionRepository permissionRepository;

    @Autowired
    public RoleServiceImpl(
            RoleRepository roleRepository,
            PermissionRepository permissionRepository,
            PermissionService permissionService
    ) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.permissionService = permissionService;
    }

    @Override
    @Transactional
    public Role createRole(String roleName, Collection<Permission> permissions) {
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            permissions.forEach(p -> {
                Permission permission = permissionRepository.findByName(p.getName());
                if (permission == null){
                    permissionService.createPermission(p.getName());
                }
            });
            roleRepository.save(role);
        }
        return role;
    }

    @Override
    public Role updateRole(Role role) {
        return null;
    }

    @Override
    public Long deleteRole(Long id) {
        roleRepository.deleteById(id);
        return id;
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRole(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
