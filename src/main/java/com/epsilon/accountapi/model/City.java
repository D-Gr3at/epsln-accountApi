package com.epsilon.accountapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class City extends BaseIdEntity implements Serializable {

    private static final long serialVersionUID = 16L;

    private String city;

    @ManyToOne
    @JoinColumn(name = "state_fk", referencedColumnName = "id")
    private State state;
}
