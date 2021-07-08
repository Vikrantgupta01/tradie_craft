package com.viks.intuit.craft.tradie.dao;


import com.viks.intuit.craft.tradie.entity.ProjectBid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectBidRepository extends JpaRepository<ProjectBid,Long> {


}
