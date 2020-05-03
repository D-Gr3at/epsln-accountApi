package com.epsilon.accountapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class State extends BaseIdEntity implements Serializable {

    private static final long serialVersionUID = 15L;

    private String state;

    @ManyToOne
    @JoinColumn(name = "country_fk", referencedColumnName = "id")
    private Country country;

    @OneToMany(fetch =  FetchType.LAZY, mappedBy = "state")
    private List<City> cities;
}
