package com.viks.intuit.craft.tradie.service;

import com.viks.intuit.craft.tradie.dao.ProjectRepository;
import com.viks.intuit.craft.tradie.entity.Project;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;


@Component
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
        // save action plugin
        // formatted and indentation
        // logging
        // exception handling
        if (this.customerTasks == null) {
            this.customerTasks = new LinkedList<>();
            final List<Project> projects =
                    this.projectRepository.findProjectsByBidExpiryDateIsBeforeAndWinnerBidIdIsNull(java.time.LocalDateTime.now());
            //projects.stream().forEach(x-> System.out.println(x.getId() + " " +x.getBidExpiryDate()));
            this.customerTasks.addAll(projects);
            //System.out.println(">>>>>>>>>>>> IN reader");
        }
        if (this.customerTasks.isEmpty()) {
            this.customerTasks = null;
            return null;
        }
        return this.customerTasks.poll();
    }
}
