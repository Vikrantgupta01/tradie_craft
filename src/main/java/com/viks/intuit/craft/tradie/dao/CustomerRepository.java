package com.viks.intuit.craft.tradie.dao;

import com.viks.intuit.craft.tradie.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}