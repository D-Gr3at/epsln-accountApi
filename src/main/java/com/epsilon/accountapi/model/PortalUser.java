package com.epsilon.accountapi.model;

import com.epsilon.accountapi.enumeration.GenderConstant;
import com.epsilon.accountapi.enumeration.GenericStatusConstant;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.io.Serializable;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "portal_user")
public class PortalUser extends BaseIdEntity implements Serializable, UserDetails {
    private static final long serialVersionUID = 1L;

    @Column(name = "first_name", length = 80, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 80, nullable = false)
    private String lastName;

    @Column(name = "middle_name", length = 80)
    private String middleName;

    @Column(name = "email", updatable = false, unique = true, length = 100)
    private String email;

    @Column(name = "phone_number", length = 15, nullable = false)
    private String phoneNumber;

    @Column(name = "password", nullable = false, length = 16)
    private String password;

    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "last_modified_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @Column(name = "status", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private GenericStatusConstant status;

    @Column(name = "gender", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private GenderConstant gender;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id")},
    inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    @Embedded
    @Column(name = "address")
    private Address address;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        roles.forEach(role -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            role.getPermissions().forEach(permission -> {
                grantedAuthorities.add(new SimpleGrantedAuthority(permission.getPermission()));
            });
        });
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
