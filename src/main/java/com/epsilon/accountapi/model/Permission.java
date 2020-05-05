package com.epsilon.accountapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Permission extends BaseIdEntity implements Serializable {

    private static final long serialVersionUID = 13L;

    private String name;

    @ManyToMany(mappedBy = "permissions", fetch = FetchType.EAGER)
    private List<Role> roles;
}
