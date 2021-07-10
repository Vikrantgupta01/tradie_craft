package com.viks.intuit.craft.tradie.service.impl;

import com.viks.intuit.craft.tradie.entity.BidType;
import com.viks.intuit.craft.tradie.entity.Project;
import com.viks.intuit.craft.tradie.entity.ProjectBid;
import com.viks.intuit.craft.tradie.entity.ProjectStatus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectProcessTest {

    ProjectProcessor projectProcessor = new ProjectProcessor();

    @Test
    public void testWinnerBid_FIXED_FIXED() {

        final List<ProjectBid> bids = new ArrayList<>();
        bids.add(this.getProjectBid(BidType.FIXED, 500));
        bids.add(this.getProjectBid(BidType.FIXED, 600));

        final ProjectBid winnerBid = this.projectProcessor.getWinnerBid(bids, 4);
        assertEquals(500, winnerBid.getAmount());
    }

    @Test
    public void testWinnerBid_FIXED_HOURLY_1() {

        final List<ProjectBid> bids = new ArrayList<>();
        bids.add(this.getProjectBid(BidType.FIXED, 500));
        bids.add(this.getProjectBid(BidType.HOURLY, 100));

        final ProjectBid winnerBid = this.projectProcessor.getWinnerBid(bids, 6);
        assertEquals(500, winnerBid.getAmount());
    }

    @Test
    public void testWinnerBid_FIXED_HOURLY_2() {

        final List<ProjectBid> bids = new ArrayList<>();
        bids.add(this.getProjectBid(BidType.FIXED, 500));
        bids.add(this.getProjectBid(BidType.HOURLY, 100));

        final ProjectBid winnerBid = this.projectProcessor.getWinnerBid(bids, 4);
        assertEquals(100, winnerBid.getAmount());
    }

    @Test
    public void testWinnerBid_HOURLY_HOURLY() {

        final List<ProjectBid> bids = new ArrayList<>();
        bids.add(this.getProjectBid(BidType.HOURLY, 100));
        bids.add(this.getProjectBid(BidType.HOURLY, 90));

        final ProjectBid winnerBid = this.projectProcessor.getWinnerBid(bids, 4);
        assertEquals(90, winnerBid.getAmount());
    }


    @Test
    public void testProjectConfirmation() {
        final Project project = new Project();
        this.projectProcessor.confirmProject(project, 100L);
        assertEquals(100, project.getWinnerBidId());
        assertEquals(ProjectStatus.CONFIRM, project.getStatus());
    }


    private ProjectBid getProjectBid(final BidType bidType, final Integer amount) {
        final ProjectBid projectBid = new ProjectBid();
        projectBid.setBidType(bidType);
        projectBid.setAmount(amount);
        return projectBid;
    }
}
