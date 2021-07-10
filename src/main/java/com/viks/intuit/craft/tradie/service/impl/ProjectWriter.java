package com.viks.intuit.craft.tradie.service.impl;

import com.viks.intuit.craft.tradie.model.BiddingResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
@Slf4j
public class ProjectWriter implements ItemWriter<BiddingResult> {

    @Override
    public void write(final List<? extends BiddingResult> list) {
        log.info("finalising summary of this job");
        for (final BiddingResult projectBid : list) {
            if (projectBid.getContractorName() == null) {
                log.info("Project {} didnt get any bid", projectBid.getProjectTitle());
                continue;
            }
            log.info("Project {} of customer {} won by contractor {} [Bid type {} with amount aud {}]",
                    projectBid.getProjectTitle(),
                    projectBid.getCustomerName(),
                    projectBid.getContractorName(),
                    projectBid.getBidType(),
                    projectBid.getBidAmount());
        }
    }
}
