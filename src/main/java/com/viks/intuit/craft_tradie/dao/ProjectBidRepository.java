package com.viks.intuit.craft_tradie.dao;


import com.viks.intuit.craft_tradie.entity.Project;
import com.viks.intuit.craft_tradie.entity.ProjectBid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectBidRepository extends JpaRepository<ProjectBid,Long> {


}