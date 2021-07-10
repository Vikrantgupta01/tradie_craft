package com.viks.intuit.craft.tradie.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "project_bid")
@Getter
@Setter
public class ProjectBid {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "bid_amount")
    private int amount;

    @Column(name = "bid_type")
    private BidType bidType;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contractor_id")
    private Contractor contractor;

}
