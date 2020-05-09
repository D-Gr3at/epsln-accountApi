package com.epsilon.accountapi.dao;

import com.epsilon.accountapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select pu.roles from PortalUser pu inner join Role r where r.name = ?1")
    List<Role> findRoleByName(String roleName);

    List<Role> findByNameIn(List<String> names);

    Role findByName(String name);

    @Query("select pu.roles from PortalUser pu where pu.email = ?1")
    List<Role> findRolesByEmail(String email);

    @Query("select p.roles from Permission p where p.name = ?1")
    List<Role> findRolesByPermissions(String permission);
}
