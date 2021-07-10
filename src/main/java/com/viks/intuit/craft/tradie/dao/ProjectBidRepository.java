package com.viks.intuit.craft.tradie.dao;


import com.viks.intuit.craft.tradie.entity.ProjectBid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectBidRepository extends JpaRepository<ProjectBid, Long> {

    List<ProjectBid> findByProject_Id(Long projectId);
}
