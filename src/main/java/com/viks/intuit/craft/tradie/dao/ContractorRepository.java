package com.viks.intuit.craft.tradie.dao;

import com.viks.intuit.craft.tradie.entity.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractorRepository extends JpaRepository<Contractor,Long> {
}