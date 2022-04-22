package com.bezkoder.spring.login.models;

import javax.persistence.*;

@Entity
@Table(name = "type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EType name;

    public Type() {

    }

    public Type(EType name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EType getName() {
        return name;
    }

    public void setName(EType name) {
        this.name = name;
    }
}
