package com.epsilon.accountapi.model;

import javax.persistence.*;

@MappedSuperclass
public class BaseIdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    protected long id;
}
