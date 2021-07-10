package com.viks.intuit.craft.tradie.service;

import com.viks.intuit.craft.tradie.dao.ProjectBidRepository;
import com.viks.intuit.craft.tradie.entity.BidType;
import com.viks.intuit.craft.tradie.entity.Project;
import com.viks.intuit.craft.tradie.entity.ProjectBid;
import com.viks.intuit.craft.tradie.entity.ProjectStatus;
import com.viks.intuit.craft.tradie.model.BiddingResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Component
@Slf4j
public class ProjectProcessor implements ItemProcessor<Project, BiddingResult> {

    private final ProjectBidRepository projectBidRepository;

    @Override
    public BiddingResult process(final Project customerTask) {

        log.info("Processing project {}", customerTask);
        final List<ProjectBid> bids = this.projectBidRepository.findByProject_Id(customerTask.getId());
        if (CollectionUtils.isEmpty(bids)) {
            log.warn("no bid is present for this project id {}", customerTask.getId());
            customerTask.setStatus(ProjectStatus.PENDING);
            return new BiddingResult(customerTask.getTitle(), null, null, null, null);
        } else {
            Collections.sort(bids, (e1, e2) -> {
                if (e1.getBidType().equals(e2.getBidType())) {
                    return e1.getAmount() - e2.getAmount();
                } else {
                    if (e1.getBidType() == BidType.FIXED) {
                        return e1.getAmount() - e2.getAmount() * e2.getProject().getExpectedTime();
                    } else {
                        return e1.getAmount() * e1.getProject().getExpectedTime() - e2.getAmount();
                    }
                }
            });
            final ProjectBid winningBid = bids.get(0);
            final Project project = winningBid.getProject();
            project.setWinnerBidId(winningBid.getId());
            project.setStatus(ProjectStatus.CONFIRM);
            return new BiddingResult(customerTask.getTitle(), customerTask.getCustomer().getName(),
                    winningBid.getContractor().getName(), winningBid.getAmount(), winningBid.getBidType());
        }
    }
}
