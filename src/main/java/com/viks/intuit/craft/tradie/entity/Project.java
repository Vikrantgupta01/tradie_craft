package com.viks.intuit.craft.tradie.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "project")
@Data
public class Project {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private Time startDate;

    @Column(name = "end_date")
    private Time endDate;

    @Column(name = "bid_expiry_date")
    private java.time.LocalDateTime bidExpiryDate;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "winner_bid_id")
    private Long winnerBidId;

}
