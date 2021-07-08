package com.viks.intuit.craft_tradie.dao;


import com.viks.intuit.craft_tradie.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    List<Project> findProjectsByBidExpiryDateIsBeforeAndWinnerBidIdIsNull(java.time.LocalDateTime time);

}
