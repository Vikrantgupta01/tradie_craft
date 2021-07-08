package com.viks.intuit.craft_tradie.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "project_bid")
@Data
@ToString
public class ProjectBid {

    @Id
    @GeneratedValue
    private Long id;


    @Column(name = "bid_amount")
    private int amount;

    @ManyToOne(targetEntity = Project.class)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(targetEntity = Contractor.class)
    @JoinColumn(name = "contractor_id")
    private Contractor contractor;

}