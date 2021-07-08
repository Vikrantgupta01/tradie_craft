package com.viks.intuit.craft_tradie.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@Data
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;
}
