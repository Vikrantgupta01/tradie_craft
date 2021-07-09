package com.viks.intuit.craft.tradie.service;

import com.viks.intuit.craft.tradie.dao.ProjectBidRepository;
import com.viks.intuit.craft.tradie.entity.Project;
import com.viks.intuit.craft.tradie.entity.ProjectBid;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Component
public class ProjectProcessor implements ItemProcessor<Project, ProjectBid> {

    private final ProjectBidRepository projectBidRepository;

    @Override
    public ProjectBid process(final Project customerTask) throws Exception {
        System.out.println("in proecessot" + customerTask.getId());
        final List<ProjectBid> bids = this.projectBidRepository.findByProject_Id(customerTask.getId());
        if (CollectionUtils.isEmpty(bids)) {
            System.out.println("no bid is present for this project ");
        } else {
            Collections.sort(bids, Comparator.comparingInt(ProjectBid::getAmount).reversed());
            final ProjectBid winningBid = bids.get(0);
            final Project project = winningBid.getProject();
            project.setWinnerBidId(winningBid.getId());
            return bids.get(0);
        }
        return null;
    }
}
