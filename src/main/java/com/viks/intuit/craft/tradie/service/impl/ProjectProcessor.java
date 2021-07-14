package com.viks.intuit.craft.tradie.service.impl;

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

    @Override
    public BiddingResult process(final Project customerTask) {

        log.info("Processing project {}", customerTask.getTitle());
        final List<ProjectBid> bids = customerTask.getBids();
        if (CollectionUtils.isEmpty(bids)) {
            log.warn("No bid is present for this project id {}", customerTask.getId());
            customerTask.setStatus(ProjectStatus.PENDING);
            return BiddingResult.builder().projectTitle(customerTask.getTitle()).build();
        } else {
            final ProjectBid winningBid = this.getWinnerBid(bids, customerTask.getExpectedTime());
            this.confirmProject(customerTask, winningBid.getId());
            return BiddingResult.builder()
                    .projectTitle(customerTask.getTitle())
                    .bidAmount(winningBid.getAmount())
                    .bidType(winningBid.getBidType())
                    .customerName(customerTask.getCustomer().getName())
                    .contractorName(winningBid.getContractor().getName())
                    .build();
        }
    }

    ProjectBid getWinnerBid(final List<ProjectBid> bids, final Integer projectTime) {
        Collections.sort(bids, (e1, e2) -> {
            if (e1.getBidType().equals(e2.getBidType())) {
                return e1.getAmount() > e2.getAmount() ? 1 : -1;
            } else {
                if (e1.getBidType() == BidType.FIXED) {
                    return e1.getAmount() > e2.getAmount() * projectTime ? 1 : -1;
                } else {
                    return e1.getAmount() * projectTime > e2.getAmount() ? 1 : -1;
                }
            }
        });
        return bids.get(0);
    }

    void confirmProject(final Project project, final Long winningBidId) {
        project.setWinnerBidId(winningBidId);
        project.setStatus(ProjectStatus.CONFIRM);
    }
}
