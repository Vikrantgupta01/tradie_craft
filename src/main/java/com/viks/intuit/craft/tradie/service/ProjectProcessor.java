package com.viks.intuit.craft.tradie.service;

import com.viks.intuit.craft.tradie.dao.ProjectBidRepository;
import com.viks.intuit.craft.tradie.entity.Project;
import com.viks.intuit.craft.tradie.entity.ProjectBid;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Component
public class ProjectProcessor implements ItemProcessor<Project, ProjectBid> {

    private ProjectBidRepository projectBidRepository;

    @Override
    public ProjectBid process(Project customerTask) throws Exception{
        System.out.println(">>>>>>>>>>>> IN processor");
        List<ProjectBid>  bids = projectBidRepository.findAll();
        Collections.sort(bids, Comparator.comparingInt(ProjectBid::getAmount).reversed());
        System.out.println(bids.get(0).getAmount());
        ProjectBid winningBid =  bids.get(0);
        Project project = winningBid.getProject();
        project.setWinnerBidId(winningBid.getId());


        //  if no bidder for that project -  status update something so it woudnt part of next batch system.
        return bids.get(0);
    }
}
