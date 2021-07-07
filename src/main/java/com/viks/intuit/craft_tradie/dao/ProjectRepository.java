package com.viks.intuit.craft_tradie.dao;

import com.viks.intuit.craft_tradie.entity.Customer;
import com.viks.intuit.craft_tradie.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
}
