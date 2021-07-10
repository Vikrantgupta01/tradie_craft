package com.viks.intuit.craft.tradie.service;

import com.viks.intuit.craft.tradie.entity.ProjectBid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
@Slf4j
public class ProjectWriter implements ItemWriter<ProjectBid> {

    @Override
    public void write(final List<? extends ProjectBid> list) {
        log.info("finalising summary of this job");
        for (final ProjectBid projectBid : list) {
            log.info("Project {} of customer {} won by contractor {}",
                    projectBid.getProject().getTitle(),
                    projectBid.getProject().getCustomer().getName(),
                    projectBid.getContractor().getName());
        }
    }
}
