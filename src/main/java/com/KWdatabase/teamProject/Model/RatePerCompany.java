package com.KWdatabase.teamProject.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RatePerCompany {
    private String itemCode;
    private String itemName;
    private long purchase;
    private long appraisal;
    private float totalRate;
    private int itemNumber;
}
