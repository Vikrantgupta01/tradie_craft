package com.viks.intuit.craft.tradie.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "project")
@Data
@ToString
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

    @Column(name = "bid_expiry_date")
    private java.time.LocalDateTime bidExpiryDate;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "winner_bid_id")
    private Long winnerBidId;

}
