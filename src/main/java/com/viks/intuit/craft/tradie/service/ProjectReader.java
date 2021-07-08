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

    private ProjectRepository projectRepository;

    public ProjectReader(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project read(){

        if(customerTasks==null){
            customerTasks = new LinkedList<>();
            List<Project> projects =
                    projectRepository.findProjectsByBidExpiryDateIsBeforeAndWinnerBidIdIsNull(java.time.LocalDateTime.now());
            projects.stream().forEach(x-> System.out.println(x.getId() + " " +x.getBidExpiryDate()));
            customerTasks.addAll(projects);
            System.out.println(">>>>>>>>>>>> IN reader");
        }
        if(customerTasks.isEmpty()){
            customerTasks =null;
            return null;
        }else{
            return customerTasks.poll();
        }
    }
}
