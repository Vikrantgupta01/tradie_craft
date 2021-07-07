package com.viks.intuit.craft_tradie.entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "project")
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
    private Time bidExpiryDate;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Time getStartDate() {
        return startDate;
    }

    public void setStartDate(Time startDate) {
        this.startDate = startDate;
    }

    public Time getEndDate() {
        return endDate;
    }

    public void setEndDate(Time endDate) {
        this.endDate = endDate;
    }

    public Time getBidExpiryDate() {
        return bidExpiryDate;
    }

    public void setBidExpiryDate(Time bidExpiryDate) {
        this.bidExpiryDate = bidExpiryDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
