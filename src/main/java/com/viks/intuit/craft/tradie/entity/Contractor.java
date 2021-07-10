package com.viks.intuit.craft.tradie.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "contractor")
@Data
public class Contractor {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;
}
