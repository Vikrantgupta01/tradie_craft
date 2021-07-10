package com.viks.intuit.craft.tradie.dao;


import com.viks.intuit.craft.tradie.entity.Project;
import com.viks.intuit.craft.tradie.entity.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findProjectsByBidExpiryDateIsBeforeAndStatus(LocalDateTime time, ProjectStatus projectStatus);
}
