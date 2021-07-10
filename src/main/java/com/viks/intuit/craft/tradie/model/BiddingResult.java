package com.viks.intuit.craft.tradie.model;

import com.viks.intuit.craft.tradie.entity.BidType;
import lombok.Value;

@Value
public class BiddingResult {

    private String projectTitle;
    private String customerName;
    private String contractorName;
    private Integer bidAmount;
    private BidType bidType;
}
