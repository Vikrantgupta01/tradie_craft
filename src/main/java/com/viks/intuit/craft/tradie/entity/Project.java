package com.viks.intuit.craft.tradie.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "project")
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "expected_time")
    private Integer expectedTime;

    @Column(name = "status")
    private ProjectStatus status;

    @Column(name = "bid_expiry_date")
    private LocalDateTime bidExpiryDate;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "winner_bid_id")
    private Long winnerBidId;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ProjectBid> bids;
}
