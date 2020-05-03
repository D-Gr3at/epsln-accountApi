package com.epsilon.accountapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Country extends BaseIdEntity implements Serializable {

    private static final long serialVersionUID = 14L;

    @Column(length = 2)
    private String iso;

    @Column(length = 80)
    private String name;

    @Column(length = 80)
    private String niceName;

    @Column(length = 3)
    private String iso3;

    @Column(length = 6)
    private long numCode;

    @Column(length = 5)
    private long phoneCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    private List<State> states;
}
