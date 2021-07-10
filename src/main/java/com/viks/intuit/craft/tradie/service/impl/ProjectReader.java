package com.viks.intuit.craft.tradie.service.impl;

import com.viks.intuit.craft.tradie.entity.Project;
import com.viks.intuit.craft.tradie.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.LinkedList;


@Component
@Slf4j
public class ProjectReader implements ItemReader<Project> {

    private LinkedList<Project> customerTasks;

    private final ProjectService projectService;

    public ProjectReader(final ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public Project read() {

        if (this.customerTasks == null) {
            log.info("Started batch job to fetch pending project");
            this.customerTasks = new LinkedList<>();
            this.customerTasks.addAll(this.projectService.getProjectsForConfirmation());
        }
        if (this.customerTasks.isEmpty()) {
            this.customerTasks = null;
            return null;
        }
        return this.customerTasks.poll();
    }
}
