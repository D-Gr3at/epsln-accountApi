package com.epsilon.accountapi.dao;

import com.epsilon.accountapi.model.Permission;
import com.epsilon.accountapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findPermissionByName(String roleName);

    List<Permission> findByNameIn(List<String> names);

    Permission findByName(String name);

    @Query("select r.permissions from Role r where r.name =?1")
    List<Permission> findPermissionByRole(String role);
}
