package com.viks.intuit.craft.tradie.service;

import com.viks.intuit.craft.tradie.dao.ProjectRepository;
import com.viks.intuit.craft.tradie.entity.Project;
import com.viks.intuit.craft.tradie.entity.ProjectStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


@Component
@Slf4j
public class ProjectReader implements ItemReader<Project> {

    private LinkedList<Project> customerTasks;

    private final ProjectRepository projectRepository;

    public ProjectReader(final ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project read() {
        // todo:: clean code : access of service layer instead of direct jpa repositorty
        // todo:: use of optional
        // exception handling
        if (this.customerTasks == null) {
            log.info("Started batch job to fetch pending project");
            this.customerTasks = new LinkedList<>();
            final List<Project> projects =
                    this.projectRepository.findProjectsByBidExpiryDateIsBeforeAndStatus(LocalDateTime.now(), ProjectStatus.NEW);
            this.customerTasks.addAll(projects);

        }
        if (this.customerTasks.isEmpty()) {
            this.customerTasks = null;
            return null;
        }
        return this.customerTasks.poll();
    }
}
