package com.viks.intuit.craft.tradie.service.impl;

import com.viks.intuit.craft.tradie.dao.ProjectRepository;
import com.viks.intuit.craft.tradie.entity.Project;
import com.viks.intuit.craft.tradie.entity.ProjectStatus;
import com.viks.intuit.craft.tradie.service.ProjectService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public List<Project> getProjectsForConfirmation() {
        return this.projectRepository.findProjectsByBidExpiryDateIsBeforeAndStatus(LocalDateTime.now(), ProjectStatus.NEW);
    }
}
